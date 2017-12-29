package com.example.akshayshah.sampleexample.crudActivity.domain.usecase;

import com.example.akshayshah.sampleexample.UseCase;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.data.source.DataSource;

/**
 * Created by akshay.shah on 28/12/17.
 */

public class AddUser extends UseCase<AddUser.Request, AddUser.Response> {

    private final DataRepository mDataRepository;

    public AddUser(DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    @Override
    protected void executeUseCase(AddUser.Request requestValues) {
        mDataRepository.putUser(requestValues.getmUser(), new DataSource.UserPutCallback() {
            @Override
            public void onInsertSuccess() {
                getUseCaseCallBack().onSuccess(new Response("Success"));
            }

            @Override
            public void onInsertFail() {
                getUseCaseCallBack().onFailure();
            }
        });


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
