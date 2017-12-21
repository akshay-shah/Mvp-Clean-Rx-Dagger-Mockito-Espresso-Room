package com.example.akshayshah.sampleexample.crudActivity;

import com.example.akshayshah.sampleexample.BasePresenter;
import com.example.akshayshah.sampleexample.BaseView;
import com.example.akshayshah.sampleexample.data.User;

import java.util.List;

/**
 * Created by akshay.shah on 08/12/17.
 */

public interface MainContract {

    interface Presenter extends BasePresenter{
        void addUser(User user);

        void removeUser(User user);
        void putUsers(List<User> users);
        void getUsers();
    }
    interface View extends BaseView<Presenter>{
        void addError(String msg);
        void addSuccess(String msg);
        void removeError(String msg);
        void removeSuccess(String msg);
        void allUserPutSuccess(String msg);
        void allUserPutError(String msg);
        void allUserGetSuccess(List<User> user);
        void allUserGetError(String msg);
    }
}
