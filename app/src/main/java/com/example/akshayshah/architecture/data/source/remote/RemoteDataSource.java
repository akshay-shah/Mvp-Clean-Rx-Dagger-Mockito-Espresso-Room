package com.example.akshayshah.architecture.data.source.remote;

import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataSource;
import com.example.akshayshah.architecture.data.source.local.UserDAO;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by akshay.shah on 08/12/17.
 */

public class RemoteDataSource implements DataSource {

    private UserDAO mUserDao;

    @Inject
    public RemoteDataSource(UserDAO mUserDao){
        this.mUserDao = mUserDao;
    }


    @Override
    public void putUser(User user, UserPutCallback callback) {

    }

    @Override
    public void removeUser(User user, UserRemoveCallback callback) {

    }

    @Override
    public Flowable<List<User>> getAllUsers() {
        return null;
    }

    @Override
    public void putAllusers(List<User> users, UserListPutCallback callback) {

    }


}