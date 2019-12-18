package com.j7arsen.filenavigator.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import moxy.MvpPresenter;
import moxy.MvpView;

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    protected CompositeDisposable compositeDisposable;

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    public void addDisposable(Disposable disposable) {
        if (disposable != null) {
            getCompositeDisposable().add(disposable);
        }
    }

    public void unDisposable(Disposable disposable) {
        if (getCompositeDisposable().size() != 0) {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
                getCompositeDisposable().remove(disposable);
            }
        }
    }

    public void unDisposableAll() {
        if (!getCompositeDisposable().isDisposed()) {
            getCompositeDisposable().clear();
            getCompositeDisposable().dispose();
        }
    }

    @Override
    public void onDestroy() {
        unDisposableAll();
        super.onDestroy();
    }

    public abstract void onBackPressed();
}
