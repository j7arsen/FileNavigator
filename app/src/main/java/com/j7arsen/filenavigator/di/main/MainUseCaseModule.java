package com.j7arsen.filenavigator.di.main;

import com.j7arsen.domain.executor.IThreadExecutor;
import com.j7arsen.domain.interactor.GetNavigationDataUseCase;
import com.j7arsen.domain.repository.INavigationDataRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MainUseCaseModule {

    @Provides
    @MainScope
    public GetNavigationDataUseCase getNavigationDataUseCase(INavigationDataRepository navigationDataRepository, @Named("io_main_background") IThreadExecutor threadExecutor) {
        return new GetNavigationDataUseCase(threadExecutor, navigationDataRepository);
    }

}
