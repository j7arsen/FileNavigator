package com.j7arsen.domain.interactor.base.completable;

import com.j7arsen.domain.executor.IThreadExecutor;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;

public abstract class BaseCompletableUseCaseWithParams<PARAMS> {

    private CompositeDisposable disposables;
    private IThreadExecutor threadExecutor;

    protected abstract Completable buildUseCase(PARAMS params);

    public BaseCompletableUseCaseWithParams(IThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
    }


    public void execute(final PARAMS params, final DisposableCompletableObserver disposableSubscriber) {
        final Completable response = getResponse(params);
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

    private Completable getResponse(final PARAMS params) {
        return buildUseCase(params)
                .subscribeOn(threadExecutor.subscribeOn())
                .observeOn(threadExecutor.observeOn());
    }

}
