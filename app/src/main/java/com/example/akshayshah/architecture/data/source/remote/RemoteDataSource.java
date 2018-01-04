package com.example.akshayshah.architecture.data.source.remote;

import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataSource;
import com.example.akshayshah.architecture.data.source.local.UserDAO;
import com.example.akshayshah.architecture.utils.schedulers.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

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
    public Single<AddUser.Response> putUser(User user) {
        return null;
    }

    @Override
    public Single<RemoveUser.Response> removeUser(User user) {
        return null;
    }

    @Override
    public Flowable<GetAllUsers.Response> getAllUsers() {
        return null;
    }


    @Override
    public Observable<AddAllUsers.Response> putAllusers(List<User> users) {
        return null;
    }


}
