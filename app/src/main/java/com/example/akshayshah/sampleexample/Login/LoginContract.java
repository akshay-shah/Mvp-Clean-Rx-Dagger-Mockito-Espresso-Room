package com.example.akshayshah.sampleexample.Login;

import com.example.akshayshah.sampleexample.BasePresenter;
import com.example.akshayshah.sampleexample.BaseView;

/**
 * Created by akshay.shah on 08/12/17.
 */

public interface LoginContract {
    interface Presenter extends BasePresenter{
        void login();
        void logout();
    }
    interface View extends BaseView<Presenter>{
        void loginError(String msg);
        void loginSuccess(String msg);
        void logoutSucces(String msg);
        void logoutError(String msg);
    }
}
