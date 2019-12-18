package com.j7arsen.filenavigator.di;

import androidx.annotation.VisibleForTesting;

import com.j7arsen.filenavigator.di.app.AppComponent;
import com.j7arsen.filenavigator.di.app.DaggerAppComponent;
import com.j7arsen.filenavigator.di.main.MainComponent;

public class ComponentManager {

    private static ComponentManager componentManager;

    private AppComponent appComponent;
    private MainComponent mainComponent;

    public static ComponentManager getInstance() {
        if (componentManager == null) {
            componentManager = new ComponentManager();
        }
        return componentManager;
    }

    public void initAppComponent() {
        appComponent = DaggerAppComponent.create();
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public MainComponent getMainComponent() {
        if (mainComponent == null) {
            mainComponent = getAppComponent().mainComponentBuilder().build();
        }
        return mainComponent;
    }

    public void destroyMainComponent() {
        mainComponent = null;
    }

}
