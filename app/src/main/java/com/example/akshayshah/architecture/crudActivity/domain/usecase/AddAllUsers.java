package com.example.akshayshah.architecture.crudActivity.domain.usecase;

import com.example.akshayshah.architecture.UseCase;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataRepository;
import com.example.akshayshah.architecture.data.source.DataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by akshay.shah on 28/12/17.
 */

public class AddAllUsers extends UseCase<AddAllUsers.Request, AddAllUsers.Response> {

    private final DataRepository mDataRepository;

    public AddAllUsers(DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    @Override
    protected Observable<AddAllUsers.Response> createObservable(Request mRequestValues) {
        return mDataRepository.putAllusers(mRequestValues.getmUserList());
    }

    public static final class Request implements UseCase.Request {
        private final List<User> mUserList;

        public Request(List<User> mUserList) {
            this.mUserList = mUserList;
        }

        public List<User> getmUserList() {
            return mUserList;
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
