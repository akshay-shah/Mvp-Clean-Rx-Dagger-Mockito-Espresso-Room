package com.example.akshayshah.architecture;


import android.arch.persistence.room.Insert;
import android.util.Log;

import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.utils.schedulers.BaseSchedulerProvider;
import com.example.akshayshah.architecture.utils.schedulers.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by akshay.shah on 28/12/17.
 */

public abstract class UseCase<Q extends UseCase.Request, P extends UseCase.Response> {

    public Observable<P> executeUseCase(BaseSchedulerProvider schedulerProvider, Q requestValues) {
        return createObservable(requestValues).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui());
    }

    public Flowable<List<User>> executeFlowableUseCase(BaseSchedulerProvider schedulerProvider, Q requestValues) {
        return createFlowable(requestValues).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui());
    }

    public interface Request {
    }

    public interface Response {
    }

    protected abstract Observable<P> createObservable(Q mRequestValues);

    public Flowable<List<User>> createFlowable(Q mRequestValues) {
        return Flowable.empty();
    }

    ;
}
