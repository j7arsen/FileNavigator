package com.j7arsen.domain.executor;

import io.reactivex.Scheduler;

public interface IThreadExecutor {

    Scheduler subscribeOn();

    Scheduler observeOn();

}
