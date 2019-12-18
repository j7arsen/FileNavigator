package com.j7arsen.filenavigator.executor;

import com.j7arsen.domain.executor.IThreadExecutor;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IoMainThreadExecutor implements IThreadExecutor {

    @Override
    public Scheduler subscribeOn() {
        return Schedulers.io();
    }

    @Override
    public Scheduler observeOn() {
        return AndroidSchedulers.mainThread();
    }
}
