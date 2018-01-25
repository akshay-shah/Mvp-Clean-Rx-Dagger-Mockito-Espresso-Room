package com.example.akshayshah.architecture.data.source;

import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.architecture.data.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by akshay.shah on 08/12/17.
 */

public interface DataSource {

    Single<AddUser.Response> putUser(User user);

    Single<RemoveUser.Response> removeUser(User user);

    Flowable<List<User>> getAllUsers();

    Observable<AddAllUsers.Response> putAllusers(List<User> users);

}
