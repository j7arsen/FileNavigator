package com.j7arsen.filenavigator.di.app.module;


import com.j7arsen.domain.executor.IThreadExecutor;
import com.j7arsen.filenavigator.di.app.AppScope;
import com.j7arsen.filenavigator.executor.IoMainThreadExecutor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ThreadingModule {

    @Provides
    @AppScope
    @Named("io_main_background")
    public IThreadExecutor provideIoMainScheduler() {
        return new IoMainThreadExecutor();
    }

}
