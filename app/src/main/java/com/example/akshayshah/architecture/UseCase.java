package com.example.akshayshah.architecture;


import android.arch.persistence.room.Insert;
import android.util.Log;

import com.example.akshayshah.architecture.utils.schedulers.BaseSchedulerProvider;
import com.example.akshayshah.architecture.utils.schedulers.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by akshay.shah on 28/12/17.
 */

public abstract class UseCase<Q extends UseCase.Request, P extends UseCase.Response> {

    public Observable<P> executeUseCase(BaseSchedulerProvider schedulerProvider, Q requestValues) {
        return createObservable(requestValues).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui());
    }

    public interface Request {
    }

    public interface Response {
    }

    protected abstract Observable<P> createObservable(Q mRequestValues);
}
