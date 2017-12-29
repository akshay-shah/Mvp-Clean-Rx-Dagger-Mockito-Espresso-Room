package com.example.akshayshah.architecture;

/**
 * Created by akshay.shah on 28/12/17.
 */

public class UseCaseHandler {
    private static UseCaseHandler INSTANCE;

    private final UseCaseScheduler mUseCaseScheduler;

    public UseCaseHandler(UseCaseScheduler useCaseScheduler) {
        mUseCaseScheduler = useCaseScheduler;
    }

    public <T extends UseCase.Request, R extends UseCase.Response> void execute(
            final UseCase<T, R> useCase, T values, UseCase.UseCaseCallBack<R> callback) {
        useCase.setmRequestValues(values);
        useCase.setUseCaseCallBack(new UiCallbackWrapper(callback, this));
        mUseCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run();
            }
        });
    }

    public <V extends UseCase.Response> void notifyResponse(final V response,
                                                            final UseCase.UseCaseCallBack<V> UseCaseCallBack) {
        mUseCaseScheduler.notifyResponse(response, UseCaseCallBack);
    }

    private <V extends UseCase.Response> void notifyError(
            final UseCase.UseCaseCallBack<V> UseCaseCallBack) {
        mUseCaseScheduler.onError(UseCaseCallBack);
    }

    private static final class UiCallbackWrapper<V extends UseCase.Response> implements
            UseCase.UseCaseCallBack<V> {
        private final UseCase.UseCaseCallBack<V> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        public UiCallbackWrapper(UseCase.UseCaseCallBack<V> callback,
                                 UseCaseHandler useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }

        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        @Override
        public void onFailure() {
            mUseCaseHandler.notifyError(mCallback);
        }

    }

    public static UseCaseHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }
}
