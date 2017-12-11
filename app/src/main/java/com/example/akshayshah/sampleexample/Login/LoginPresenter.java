package com.example.akshayshah.sampleexample.Login;

import com.example.akshayshah.sampleexample.BasePresenter;
import com.example.akshayshah.sampleexample.R;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.data.source.DataSource;

import javax.inject.Inject;

/**
 * Created by akshay.shah on 08/12/17.
 */

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mLoginView;
    private DataSource mRepository;

    @Inject
    public LoginPresenter(LoginContract.View view,DataSource mRepository){
        mLoginView = view;
        this.mRepository = mRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        mLoginView = null;
    }

    @Override
    public void login() {
        mRepository.putUser(new User(2, "shriram"), new DataSource.UserPutCallback() {
            @Override
            public void onInsertSuccess() {
                mLoginView.loginSuccess("SuccessFull");
            }

            @Override
            public void onInsertFail() {
                mLoginView.loginError("Failure");
            }
        });
    }

    @Override
    public void logout() {
        mRepository.removeUser(new User(1, "akshay"), new DataSource.UserRemoveCallback() {
            @Override
            public void onRemoveSuccess() {
                mLoginView.logoutSucces("Success");
            }

            @Override
            public void onRemoveFail() {
                mLoginView.logoutError("Error");
            }
        });
    }

}
