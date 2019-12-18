package com.j7arsen.data.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.j7arsen.data.R;
import com.j7arsen.data.app.Environment;
import com.j7arsen.data.exception.FileCloseException;
import com.j7arsen.data.exception.FileReadException;
import com.j7arsen.data.exception.JsonParseException;
import com.j7arsen.data.model.data.NavigationDataEntity;
import com.j7arsen.data.model.mapper.IObjectModelMapper;
import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.domain.repository.INavigationDataRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class NavigationDataRepository implements INavigationDataRepository {

    private Context context;
    private Gson gson;
    private IObjectModelMapper<NavigationDataEntity, NavigationDataModel> navigationDataMapper;

    public NavigationDataRepository(Context context, Gson gson, IObjectModelMapper<NavigationDataEntity, NavigationDataModel> navigationDataMapper) {
        this.context = context;
        this.gson = gson;
        this.navigationDataMapper = navigationDataMapper;
    }

    @Override
    public Observable<NavigationDataModel> getNavigationData() {
        return Observable.create(new ObservableOnSubscribe<NavigationDataEntity>() {
            @Override
            public void subscribe(ObservableEmitter<NavigationDataEntity> emitter) throws Exception {
                try {
                    emitter.onNext(readFromFile());
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }).map(data -> navigationDataMapper.mapEntityToDomain(data));
    }

    public NavigationDataEntity readFromFile() throws JsonParseException, FileReadException, FileCloseException {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getAssets().open(Environment.FILE_NAME);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
            NavigationDataEntity navigationDataEntity = gson.fromJson(returnString.toString(), NavigationDataEntity.class);
            return navigationDataEntity;
        } catch (Exception e) {
            if (e instanceof JsonSyntaxException) {
                throw new JsonParseException(context.getResources().getString(R.string.json_exception_message));
            } else {
                throw new FileReadException(context.getResources().getString(R.string.file_read_exception_message));
            }
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                throw new FileCloseException(context.getResources().getString(R.string.file_close_exception_message));
            }
        }
    }

}
