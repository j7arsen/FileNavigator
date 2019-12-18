package com.j7arsen.filenavigator.ui.navigation;

import com.j7arsen.domain.interactor.GetNavigationDataUseCase;
import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.filenavigator.R;
import com.j7arsen.filenavigator.base.BasePresenter;
import com.j7arsen.filenavigator.di.ComponentManager;
import com.j7arsen.filenavigator.utils.ResUtils;
import com.j7arsen.filenavigator.utils.Screens;
import com.j7arsen.filenavigator.utils.error.ErrorData;
import com.j7arsen.filenavigator.utils.error.ErrorHandler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class NavigationPresenter extends BasePresenter<INavigationView> {

    private Router router;
    private ResUtils resUtils;

    @Inject
    ErrorHandler errorHandler;

    @Inject
    GetNavigationDataUseCase getNavigationDataUseCase;

    private NavigationDataModel navigationDataModel;

    public NavigationPresenter(Router router, ResUtils resUtils, NavigationDataModel navigationDataModel) {
        ComponentManager.getInstance().getMainComponent().inject(this);
        this.router = router;
        this.resUtils = resUtils;
        this.navigationDataModel = navigationDataModel;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        if (navigationDataModel == null) {
            loadNavigationData();
        } else {
            getViewState().setData(navigationDataModel.getNavigationDataModelList());
        }
    }

    public void loadNavigationData() {
        getNavigationDataUseCase.execute(new DisposableObserver<NavigationDataModel>() {
            @Override
            protected void onStart() {
                getViewState().hideEmptyListView();
                getViewState().showProgress();
            }

            @Override
            public void onNext(NavigationDataModel navigationDataModel) {
                if (navigationDataModel == null) {
                    getViewState().showEmptyListView();
                } else {
                    List<NavigationDataModel> navigationDataModelList = new ArrayList<>();
                    navigationDataModelList.add(navigationDataModel);
                    getViewState().setData(navigationDataModelList);
                }
                getViewState().hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                ErrorData errorData = errorHandler.getError(e);
                getViewState().showError(errorData);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void onItemClick(NavigationDataModel navigationDataModel) {
        if (navigationDataModel != null) {
            switch (navigationDataModel.getType()) {
                case FOLDER:
                    if (navigationDataModel.getNavigationDataModelList() != null && navigationDataModel.getNavigationDataModelList().size() != 0) {
                        router.navigateTo(new Screens.NavigatorScreen(navigationDataModel));
                    } else {
                        getViewState().showMessage(resUtils.getString(R.string.folder_empty));
                    }
                    break;
                case FILE:
                    if (navigationDataModel.getContent() != null && !navigationDataModel.getContent().isEmpty()) {
                        router.navigateTo(new Screens.FileDetailScreen(navigationDataModel));
                    } else {
                        getViewState().showMessage(resUtils.getString(R.string.file_empty));
                    }
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        getNavigationDataUseCase.dispose();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }
}
