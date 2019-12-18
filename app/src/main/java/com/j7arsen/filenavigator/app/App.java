package com.j7arsen.filenavigator.app;

import android.app.Application;

import com.j7arsen.filenavigator.di.ComponentManager;

public class App extends Application {

    public static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ComponentManager.getInstance().initAppComponent();
    }
}
