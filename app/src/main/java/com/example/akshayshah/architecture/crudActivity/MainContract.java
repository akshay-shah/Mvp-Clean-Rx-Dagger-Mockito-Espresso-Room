package com.example.akshayshah.architecture.crudActivity;

import com.example.akshayshah.architecture.BaseView;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.BasePresenter;

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

    interface View extends BaseView<Presenter> {
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
