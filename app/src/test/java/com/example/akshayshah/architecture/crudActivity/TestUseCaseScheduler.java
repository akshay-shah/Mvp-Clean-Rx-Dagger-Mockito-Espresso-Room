package com.example.akshayshah.architecture.crudActivity;

import com.example.akshayshah.architecture.UseCase;
import com.example.akshayshah.architecture.UseCaseScheduler;

/**
 * Created by akshay.shah on 29/12/17.
 */

public class TestUseCaseScheduler implements UseCaseScheduler {
    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }

    @Override
    public <V extends UseCase.Response> void notifyResponse(V response, UseCase.UseCaseCallBack<V> useCaseCallback) {
        useCaseCallback.onSuccess(response);
    }

    @Override
    public <V extends UseCase.Response> void onError(UseCase.UseCaseCallBack<V> useCaseCallback) {
        useCaseCallback.onFailure();
    }
}
