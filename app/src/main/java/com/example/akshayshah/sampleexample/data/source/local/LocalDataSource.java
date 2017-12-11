package com.example.akshayshah.sampleexample.data.source.local;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataSource;

import java.util.List;


import javax.inject.Inject;

/**
 * Created by akshay.shah on 08/12/17.
 */

public class LocalDataSource implements DataSource {

    private UserDAO mUserDao;

    @Inject
    public LocalDataSource(UserDAO mUserDao){
        this.mUserDao = mUserDao;
    }


    @Override
    public void putUser(final User user, final UserPutCallback callback) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mUserDao.putUser(user);
                List<User> users = mUserDao.getUser();
                for(User u : users)
                    Log.d("USERS","USER ID = "+u.getUserId()+" USERNAME ="+u.getUserName());
            }
        });
        thread.start();
        callback.onInsertSuccess();

    }

    @Override
    public void removeUser(final User user, UserRemoveCallback callback) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mUserDao.deleteUser(user.getUserId());
                List<User> users = mUserDao.getUser();
                for(User u : users)
                    Log.d("USERS","USER ID = "+u.getUserId()+" USERNAME ="+u.getUserName());
            }
        });
        thread.start();
        callback.onRemoveSuccess();
    }

}
