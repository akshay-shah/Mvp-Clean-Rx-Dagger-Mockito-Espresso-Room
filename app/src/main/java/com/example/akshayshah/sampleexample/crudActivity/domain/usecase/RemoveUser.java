package com.example.akshayshah.sampleexample.crudActivity.domain.usecase;

import com.example.akshayshah.sampleexample.UseCase;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.data.source.DataSource;

import javax.inject.Inject;

/**
 * Created by akshay.shah on 28/12/17.
 */

public class RemoveUser extends UseCase<RemoveUser.Request, RemoveUser.Response> {

    private DataRepository mDataRepository;

    public RemoveUser(DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    @Override
    protected void executeUseCase(RemoveUser.Request requestValues) {
        mDataRepository.removeUser(requestValues.getmUser(), new DataSource.UserRemoveCallback() {
            @Override
            public void onRemoveSuccess() {
                getUseCaseCallBack().onSuccess(new Response("Remove Success"));
            }

            @Override
            public void onRemoveFail() {
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
