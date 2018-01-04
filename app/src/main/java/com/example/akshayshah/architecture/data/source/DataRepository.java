package com.example.akshayshah.architecture.data.source;

import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.local.LocalDataSource;
import com.example.akshayshah.architecture.data.source.remote.RemoteDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

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
    public Single<AddUser.Response> putUser(User user) {
        return mLocalDataSource.putUser(user);
    }

    @Override
    public Single<RemoveUser.Response> removeUser(User user) {
        return mLocalDataSource.removeUser(user);
    }

    @Override
    public Flowable<GetAllUsers.Response> getAllUsers() {
        return mLocalDataSource.getAllUsers();
    }


    @Override
    public Observable<AddAllUsers.Response> putAllusers(List<User> users) {
        return mLocalDataSource.putAllusers(users);
    }


}
