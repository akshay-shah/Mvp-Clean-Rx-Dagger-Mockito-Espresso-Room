package com.example.akshayshah.architecture.data.source;

import com.example.akshayshah.architecture.data.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by akshay.shah on 08/12/17.
 */

public interface DataSource {

    interface UserPutCallback{
        void onInsertSuccess();
        void onInsertFail();
    }
    interface UserRemoveCallback{
        void onRemoveSuccess();
        void onRemoveFail();
    }

    interface UserListPutCallback {
        void onAllUserPut();
    }

    void putUser(User user, UserPutCallback callback);

    void removeUser(User user,UserRemoveCallback callback);

    Flowable<List<User>> getAllUsers();

    void putAllusers(List<User> users, UserListPutCallback callback);

}
