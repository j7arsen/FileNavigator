package com.j7arsen.filenavigator.ui.main;

import com.j7arsen.filenavigator.base.BasePresenter;
import com.j7arsen.filenavigator.di.ComponentManager;
import com.j7arsen.filenavigator.utils.Screens;

import ru.terrakok.cicerone.Router;

public class MainPresenter extends BasePresenter<IMainView> {

    private Router router;

    public MainPresenter(Router router) {
        ComponentManager.getInstance().getMainComponent().inject(this);
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        openAccountDetailScreen();
    }

    public void openAccountDetailScreen() {
        router.newRootScreen(new Screens.NavigatorScreen(null));
    }

    @Override
    public void onDestroy() {
        ComponentManager.getInstance().destroyMainComponent();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }

}
