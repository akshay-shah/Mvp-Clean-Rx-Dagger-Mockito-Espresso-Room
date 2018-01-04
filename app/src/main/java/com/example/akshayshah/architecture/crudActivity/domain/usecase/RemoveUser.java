package com.example.akshayshah.architecture.crudActivity.domain.usecase;

import com.example.akshayshah.architecture.data.source.DataRepository;
import com.example.akshayshah.architecture.UseCase;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataSource;

import io.reactivex.Observable;

/**
 * Created by akshay.shah on 28/12/17.
 */

public class RemoveUser extends UseCase<RemoveUser.Request, RemoveUser.Response> {

    private DataRepository mDataRepository;

    public RemoveUser(DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    @Override
    protected Observable<Response> createObservable(Request mRequestValues) {
        return mDataRepository.removeUser(mRequestValues.getmUser()).toObservable();
    }


    public static final class Request implements UseCase.Request {
        private final User mUser;

        public Request(User mUser) {
            this.mUser = mUser;
        }

        public User getmUser() {
            return mUser;
        }
    }

    public static final class Response implements UseCase.Response {
        private final String response;

        public Response(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }
    }
}
