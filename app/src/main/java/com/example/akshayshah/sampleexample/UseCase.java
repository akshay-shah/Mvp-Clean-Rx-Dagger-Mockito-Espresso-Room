package com.example.akshayshah.sampleexample;

/**
 * Created by akshay.shah on 28/12/17.
 */

public abstract class UseCase<Q extends UseCase.Request, P extends UseCase.Response> {

    private Q mRequestValues;
    private UseCaseCallBack<P> useCaseCallBack;

    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    public UseCaseCallBack<P> getUseCaseCallBack() {
        return useCaseCallBack;
    }

    public void setUseCaseCallBack(UseCaseCallBack<P> useCaseCallBack) {
        this.useCaseCallBack = useCaseCallBack;
    }

    public Q getmRequestValues() {
        return mRequestValues;
    }

    public void setmRequestValues(Q mRequestValues) {
        this.mRequestValues = mRequestValues;
    }

    public interface Request {

    }

    public interface Response {

    }

    public interface UseCaseCallBack<R> {
        void onSuccess(R response);

        void onFailure();
    }
}
