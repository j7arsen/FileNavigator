package com.j7arsen.filenavigator.di.main;

import android.content.Context;

import com.google.gson.Gson;
import com.j7arsen.data.model.data.NavigationDataEntity;
import com.j7arsen.data.model.mapper.IObjectModelMapper;
import com.j7arsen.data.repository.NavigationDataRepository;
import com.j7arsen.data.repository.mapper.NavigationDataMapper;
import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.domain.repository.INavigationDataRepository;
import com.j7arsen.filenavigator.di.app.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class MainRepositoryModule {

    @Provides
    @MainScope
    public IObjectModelMapper<NavigationDataEntity, NavigationDataModel> navigationDataMapper() {
        return new NavigationDataMapper();
    }

    @Provides
    @MainScope
    public INavigationDataRepository providePostRepository(@ApplicationContext Context context, Gson gson, IObjectModelMapper<NavigationDataEntity, NavigationDataModel> navigationDataMapper) {
        return new NavigationDataRepository(context, gson, navigationDataMapper);
    }

}
