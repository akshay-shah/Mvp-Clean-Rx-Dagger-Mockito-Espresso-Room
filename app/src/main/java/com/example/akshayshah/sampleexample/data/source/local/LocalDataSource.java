package com.example.akshayshah.sampleexample.data.source.local;

import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataSource;
import com.example.akshayshah.sampleexample.utils.AppExecutors;

import java.util.List;


import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

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

    /**
     * Using RxJava
     *
     * @return Flowable Object
     */
    @Override
    public Flowable<List<User>> getAllUsers() {
        return mUserDao.getUser();
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
                        callback.onAllUserPut();
                    }
                });
            }
        });
    }


}
