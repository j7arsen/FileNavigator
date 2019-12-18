package com.j7arsen.filenavigator.di.app.module;

import android.content.Context;

import com.j7arsen.filenavigator.di.app.AppScope;
import com.j7arsen.filenavigator.di.app.ApplicationContext;
import com.j7arsen.filenavigator.utils.ResUtils;
import com.j7arsen.filenavigator.utils.error.ErrorHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @AppScope
    @Provides
    public ResUtils provideResUtils(@ApplicationContext Context context) {
        return new ResUtils(context);
    }

    @AppScope
    @Provides
    public ErrorHandler provideErrorHandler(ResUtils utils) {
        return new ErrorHandler(utils);
    }

}
