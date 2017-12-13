package com.example.akshayshah.sampleexample.Login;

import com.example.akshayshah.sampleexample.BasePresenter;
import com.example.akshayshah.sampleexample.BaseView;
import com.example.akshayshah.sampleexample.data.User;

import java.util.List;

import javax.inject.Singleton;

/**
 * Created by akshay.shah on 08/12/17.
 */

public interface LoginContract {
    interface Presenter extends BasePresenter{
        void login();
        void logout();

        void putUsers(List<User> users);

        void getUsers();
    }
    interface View extends BaseView<Presenter>{
        void loginError(String msg);
        void loginSuccess(String msg);
        void logoutSucces(String msg);
        void logoutError(String msg);

        void allUserPutSuccess(String msg);

        void allUserPutError(String msg);
    }
}
