package com.j7arsen.domain.interactor.base.observable;

import com.j7arsen.domain.executor.IThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class BaseObservableUseCaseWithParams<DATA, PARAMS> {

    private CompositeDisposable disposables;
    private IThreadExecutor threadExecutor;

    protected abstract Observable<DATA> buildUseCase(PARAMS params);

    public BaseObservableUseCaseWithParams(IThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
    }


    public void execute(final PARAMS params, final DisposableObserver<DATA> disposableSubscriber) {
        final Observable<DATA> response = getResponse(params);
        response.doOnSubscribe(this::addDisposable)
                .subscribeWith(disposableSubscriber);
    }


    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable localDisposable) {
        if (localDisposable == null) return;
        disposables.add(localDisposable);
    }

    private Observable<DATA> getResponse(final PARAMS params) {
        return buildUseCase(params)
                .subscribeOn(threadExecutor.subscribeOn())
                .observeOn(threadExecutor.observeOn());
    }
}
