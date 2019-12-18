package com.j7arsen.filenavigator.ui.file_detail;

import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.filenavigator.base.BasePresenter;
import com.j7arsen.filenavigator.di.ComponentManager;

import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class FileDetailPresenter extends BasePresenter<IFileDetailView> {

    private Router router;

    private NavigationDataModel navigationDataModel;

    public FileDetailPresenter(Router router, NavigationDataModel navigationDataModel) {
        ComponentManager.getInstance().getMainComponent().inject(this);
        this.router = router;
        this.navigationDataModel = navigationDataModel;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showContent(navigationDataModel);
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }
}
