package com.example.akshayshah.architecture.crudActivity;

import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataSource;
import com.example.akshayshah.architecture.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by akshay.shah on 08/12/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mLoginView;
    private DataSource mRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private BaseSchedulerProvider schedulerProvider;
    private AddUser addUser;
    private AddAllUsers addAllUsers;
    private GetAllUsers getAllUsers;
    private RemoveUser removeUser;

    public MainPresenter(MainContract.View view, DataSource mRepository,
                         BaseSchedulerProvider schedulerProvider,
                         AddUser addUser,
                         GetAllUsers getAllUsers,
                         RemoveUser removeUser,
                         AddAllUsers addAllUsers) {
        mLoginView = view;
        this.mRepository = mRepository;
        this.schedulerProvider = schedulerProvider;
        this.addUser = addUser;
        this.removeUser = removeUser;
        this.getAllUsers = getAllUsers;
        this.addAllUsers = addAllUsers;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        disposable.clear();
    }


    @Override
    public void addUser(User user) {
    }

    @Override
    public void removeUser(User user) {
        RemoveUser.Request request = new RemoveUser.Request(user);
        disposable.add(removeUser.executeUseCase(schedulerProvider, request)
                .subscribe(response -> {
                            mLoginView.removeSuccess(response.getResponse());
                        },
                        throwable -> {
                            mLoginView.removeError("Error removing user");
                        }));
    }

    @Override
    public void putUsers(List<User> users) {
        AddAllUsers.Request request = new AddAllUsers.Request(users);
        disposable.add(addAllUsers.executeUseCase(schedulerProvider, request)
                .subscribe(response -> {
                            mLoginView.allUserPutSuccess(response.getResponse());
                        },
                        throwable -> {
                            mLoginView.allUserPutError("Error putting users");
                        }));
    }

    @Override
    public void getUsers() {
        GetAllUsers.Request request = new GetAllUsers.Request();
        disposable.add(getAllUsers.executeFlowableUseCase(schedulerProvider, request)
                .subscribe(response -> {
                            mLoginView.allUserGetSuccess(response);
                        },
                        throwable -> {
                            mLoginView.allUserGetError("Error getting users");
                        }));
    }

}
