package com.j7arsen.filenavigator.di.main;

import com.j7arsen.filenavigator.ui.file_detail.FileDetailFragment;
import com.j7arsen.filenavigator.ui.file_detail.FileDetailPresenter;
import com.j7arsen.filenavigator.ui.main.MainActivity;
import com.j7arsen.filenavigator.ui.main.MainPresenter;
import com.j7arsen.filenavigator.ui.navigation.NavigationFragment;
import com.j7arsen.filenavigator.ui.navigation.NavigationPresenter;

import dagger.Subcomponent;

@MainScope
@Subcomponent(modules = {MainRepositoryModule.class, MainUseCaseModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

    void inject(NavigationFragment navigationFragment);

    void inject(NavigationPresenter navigationPresenter);

    void inject(FileDetailFragment fileDetailFragment);

    void inject(FileDetailPresenter fileDetailPresenter);

    @Subcomponent.Builder
    interface Builder {
        MainComponent build();
    }

}
