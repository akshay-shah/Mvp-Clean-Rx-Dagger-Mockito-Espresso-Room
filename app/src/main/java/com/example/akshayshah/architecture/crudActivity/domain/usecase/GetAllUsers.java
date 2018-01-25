package com.example.akshayshah.architecture.crudActivity.domain.usecase;

import com.example.akshayshah.architecture.UseCase;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataRepository;
import com.example.akshayshah.architecture.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by akshay.shah on 28/12/17.
 */

public class GetAllUsers extends UseCase<GetAllUsers.Request, GetAllUsers.Response> {

    private DataRepository mDataRepository;

    public GetAllUsers(DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    @Override
    protected Observable<Response> createObservable(Request mRequestValues) {
        return null;
    }


    @Override
    public Flowable<List<User>> createFlowable(Request mRequestValues) {
        return mDataRepository.getAllUsers();
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
