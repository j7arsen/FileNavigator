package com.j7arsen.filenavigator.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.j7arsen.filenavigator.R;
import com.j7arsen.filenavigator.base.BaseContainerActivity;
import com.j7arsen.filenavigator.di.ComponentManager;
import com.j7arsen.filenavigator.di.app.Global;
import com.j7arsen.filenavigator.utils.IBackButtonListener;

import javax.inject.Inject;

import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends BaseContainerActivity implements IMainView{

    @Inject
    @Global
    NavigatorHolder navigatorHolder;

    @Inject
    @Global
    Router router;

    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    public MainPresenter createAccountDetailContainerPresenter() {
        return new MainPresenter(router);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    private Navigator navigator = new SupportAppNavigator(this, getSupportFragmentManager(), R.id.fl_container);

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (fragment != null
                && fragment instanceof IBackButtonListener
                && ((IBackButtonListener) fragment).onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }
}
