package com.example.akshayshah.sampleexample.crudActivity.domain.usecase;

import android.widget.ListView;

import com.example.akshayshah.sampleexample.UseCase;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.data.source.DataSource;

import java.util.List;

/**
 * Created by akshay.shah on 28/12/17.
 */

public class AddAllUsers extends UseCase<AddAllUsers.Request, AddAllUsers.Response> {

    private final DataRepository mDataRepository;

    public AddAllUsers(DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    @Override
    protected void executeUseCase(Request requestValues) {
        mDataRepository.putAllusers(requestValues.getmUserList(), new DataSource.UserListPutCallback() {
            @Override
            public void onAllUserPut() {
                getUseCaseCallBack().onSuccess(new Response("All users inserted"));
            }
        });
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
