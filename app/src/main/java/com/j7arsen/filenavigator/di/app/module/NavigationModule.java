package com.j7arsen.filenavigator.di.app.module;


import com.j7arsen.filenavigator.di.app.AppScope;
import com.j7arsen.filenavigator.di.app.Global;
import com.j7arsen.filenavigator.di.app.Local;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class NavigationModule {

    private Cicerone<Router> localCicerone;
    private Cicerone<Router> globalCicerone;

    public NavigationModule() {
        localCicerone = Cicerone.create();
        globalCicerone = Cicerone.create();
    }

    @AppScope
    @Provides
    @Local
    Router provideLocalRouter() {
        return localCicerone.getRouter();
    }

    @AppScope
    @Provides
    @Local
    NavigatorHolder provideLocalNavigatorHolder() {
        return localCicerone.getNavigatorHolder();
    }

    @AppScope
    @Provides
    @Global
    Router provideGlobalRouter() {
        return globalCicerone.getRouter();
    }

    @AppScope
    @Provides
    @Global
    NavigatorHolder provideGlobalNavigatorHolder() {
        return globalCicerone.getNavigatorHolder();
    }

}
