package com.example.akshayshah.architecture.data.source.local;

import android.icu.lang.UScript;

import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataSource;
import com.example.akshayshah.architecture.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Callable;


import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

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
    public Single<AddUser.Response> putUser(User user) {
        return Single.fromCallable(new Callable<AddUser.Response>() {
            @Override
            public AddUser.Response call() throws Exception {
                long size = mUserDao.putUser(user);
                return new AddUser.Response("Success " + size);
            }
        });
    }

    @Override
    public Single<RemoveUser.Response> removeUser(User user) {
        return Single.fromCallable(new Callable<RemoveUser.Response>() {
            @Override
            public RemoveUser.Response call() throws Exception {
                int removedItems = mUserDao.deleteUser(user.getUserId());
                return new RemoveUser.Response("Deleted " + removedItems);
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
    public Observable<AddAllUsers.Response> putAllusers(final List<User> users) {
        return Observable.fromCallable(new Callable<AddAllUsers.Response>() {
            @Override
            public AddAllUsers.Response call() throws Exception {
                long size = mUserDao.putAllUsers(users).size();
                return new AddAllUsers.Response("Success inserting " + size);
            }
        });
    }


}
