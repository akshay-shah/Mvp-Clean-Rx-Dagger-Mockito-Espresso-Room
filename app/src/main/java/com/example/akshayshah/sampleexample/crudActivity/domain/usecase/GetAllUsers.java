package com.example.akshayshah.sampleexample.crudActivity.domain.usecase;

import com.example.akshayshah.sampleexample.UseCase;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by akshay.shah on 28/12/17.
 */

public class GetAllUsers extends UseCase<GetAllUsers.Request, GetAllUsers.Response> {

    private DataRepository mDataRepository;
    private BaseSchedulerProvider schedulerProvider;
    private CompositeDisposable disposable = new CompositeDisposable();

    public GetAllUsers(DataRepository mDataRepository, BaseSchedulerProvider schedulerProvider) {
        this.mDataRepository = mDataRepository;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void executeUseCase(Request requestValues) {
        disposable.add(mDataRepository.getAllUsers()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        users -> {
                            if (users.size() > 0) {
                                getUseCaseCallBack().onSuccess(new Response(users));
                            } else {
                                getUseCaseCallBack().onFailure();
                            }
                        },
                        throwable -> getUseCaseCallBack().onFailure()
                ));
    }

    public static final class Request implements UseCase.Request {

    }

    public static final class Response implements UseCase.Response {

        private final List<User> mUserList;

        public Response(List<User> mUserList) {
            this.mUserList = mUserList;
        }

        public List<User> getmUserList() {
            return mUserList;
        }
    }
}
