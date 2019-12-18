package com.j7arsen.filenavigator.di.app.module;

import android.content.Context;
import android.content.res.AssetManager;

import com.j7arsen.filenavigator.app.App;
import com.j7arsen.filenavigator.di.app.AppScope;
import com.j7arsen.filenavigator.di.app.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule() {
        this.context = App.mInstance;
    }

    @AppScope
    @Provides
    @ApplicationContext
    public Context context() {
        return context.getApplicationContext();
    }

    @AppScope
    @Provides
    public AssetManager assetManager() {
        return context().getAssets();
    }

}
