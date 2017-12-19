package com.example.akshayshah.sampleexample.data.source;

import android.icu.lang.UScript;
import android.provider.ContactsContract;

import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.local.LocalDataSource;
import com.example.akshayshah.sampleexample.data.source.remote.RemoteDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by akshay.shah on 08/12/17.
 */

public class DataRepository implements DataSource {

    private LocalDataSource mLocalDataSource;
    private RemoteDataSource mRemoteDataSource;

    @Inject
    public DataRepository(LocalDataSource mLocalDataSource, RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public void putUser(User user, UserPutCallback callback) {
        mLocalDataSource.putUser(user,callback);
        mRemoteDataSource.putUser(user,callback);
    }

    @Override
    public void removeUser(User user, UserRemoveCallback callback) {
        mLocalDataSource.removeUser(user,callback);
        mRemoteDataSource.removeUser(user,callback);
    }

    @Override
    public void getAllUsers(UserLoadedCallback callback) {
        mLocalDataSource.getAllUsers(callback);
        mRemoteDataSource.getAllUsers(callback);
    }

    @Override
    public void putAllusers(List<User> users, AllUserPutCallback callback) {
        mLocalDataSource.putAllusers(users, callback);
        mRemoteDataSource.putAllusers(users, callback);
    }


}
