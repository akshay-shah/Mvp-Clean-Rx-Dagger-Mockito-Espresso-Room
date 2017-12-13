package com.example.akshayshah.sampleexample.data.source.remote;

import android.provider.ContactsContract;

import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataSource;
import com.example.akshayshah.sampleexample.data.source.local.LocalDataSource;
import com.example.akshayshah.sampleexample.data.source.local.UserDAO;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

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
    public void getAllUsers(UserLoadedCallback callback) {

    }

    @Override
    public void putAllusers(List<User> users, UserLoadedCallback callback) {

    }

}
