package com.j7arsen.domain.interactor;

import com.j7arsen.domain.executor.IThreadExecutor;
import com.j7arsen.domain.interactor.base.observable.BaseObservableUseCase;
import com.j7arsen.domain.model.NavigationDataModel;
import com.j7arsen.domain.repository.INavigationDataRepository;

import io.reactivex.Observable;

public class GetNavigationDataUseCase extends BaseObservableUseCase<NavigationDataModel> {

    private INavigationDataRepository navigationDataRepository;

    public GetNavigationDataUseCase(IThreadExecutor threadExecutor, INavigationDataRepository navigationDataRepository) {
        super(threadExecutor);
        this.navigationDataRepository = navigationDataRepository;
    }

    @Override
    protected Observable<NavigationDataModel> buildUseCase() {
        return navigationDataRepository.getNavigationData();
    }

}
