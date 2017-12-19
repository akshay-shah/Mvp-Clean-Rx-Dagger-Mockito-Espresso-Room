package com.example.akshayshah.sampleexample.data.source.local;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataSource;
import com.example.akshayshah.sampleexample.utils.AppExecutors;

import java.util.List;


import javax.inject.Inject;

/**
 * Created by akshay.shah on 08/12/17.
 */

public class LocalDataSource implements DataSource {

    private UserDAO mUserDao;
    private AppExecutors appExecutors;

    @Inject
    public LocalDataSource(UserDAO mUserDao, AppExecutors appExecutors) {
        this.mUserDao = mUserDao;
        this.appExecutors = appExecutors;
    }


    @Override
    public void putUser(final User user, final UserPutCallback callback) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.putUser(user);
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onInsertSuccess();
                    }
                });
            }
        });


    }

    @Override
    public void removeUser(final User user, final UserRemoveCallback callback) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.deleteUser(user.getUserId());
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onRemoveSuccess();
                    }
                });
            }
        });

    }

    @Override
    public void getAllUsers(final UserLoadedCallback callback) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<User> userList = mUserDao.getUser();
                for (User u : userList)
                    Log.d("USERS","USER ID = "+u.getUserId()+" USERNAME ="+u.getUserName());
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.OnUserLoaded(userList);
                    }
                });
            }
        });
    }

    @Override
    public void putAllusers(final List<User> users, final AllUserPutCallback callback) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                for (User u : users)
                    mUserDao.putUser(u);
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.OnAllUserPut();
                    }
                });
            }
        });
    }


}
