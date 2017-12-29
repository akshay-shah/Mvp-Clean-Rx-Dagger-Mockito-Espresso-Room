package com.example.akshayshah.architecture.crudActivity.domain.usecase;

import com.example.akshayshah.architecture.UseCase;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataRepository;
import com.example.akshayshah.architecture.data.source.DataSource;

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
