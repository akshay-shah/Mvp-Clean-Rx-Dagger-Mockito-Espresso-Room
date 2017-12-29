package com.example.akshayshah.sampleexample;

/**
 * Created by akshay.shah on 28/12/17.
 */

public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.Response> void notifyResponse(final V response,
                                                     final UseCase.UseCaseCallBack<V> useCaseCallback);

    <V extends UseCase.Response> void onError(
            final UseCase.UseCaseCallBack<V> useCaseCallback);

}
