package com.example.akshayshah.architecture.crudActivity;

import com.example.akshayshah.architecture.UseCase;
import com.example.akshayshah.architecture.UseCaseHandler;
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
    private final UseCaseHandler mUseCaseHandler;

    public MainPresenter(MainContract.View view, DataSource mRepository,
                         BaseSchedulerProvider schedulerProvider,
                         AddUser addUser,
                         GetAllUsers getAllUsers,
                         RemoveUser removeUser,
                         AddAllUsers addAllUsers,
                         UseCaseHandler mUseCaseHandler) {
        mLoginView = view;
        this.mRepository = mRepository;
        this.schedulerProvider = schedulerProvider;
        this.addUser = addUser;
        this.removeUser = removeUser;
        this.getAllUsers = getAllUsers;
        this.addAllUsers = addAllUsers;
        this.mUseCaseHandler = mUseCaseHandler;
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
        AddUser.Request request = new AddUser.Request(user);
        mUseCaseHandler.execute(addUser, request, new UseCase.UseCaseCallBack<AddUser.Response>() {
            @Override
            public void onSuccess(AddUser.Response response) {
                mLoginView.addSuccess(response.getResponse());
            }

            @Override
            public void onFailure() {
                mLoginView.addError("Error adding user");
            }
        });
    }

    @Override
    public void removeUser(User user) {
        RemoveUser.Request request = new RemoveUser.Request(user);
        mUseCaseHandler.execute(removeUser, request, new UseCase.UseCaseCallBack<RemoveUser.Response>() {
            @Override
            public void onSuccess(RemoveUser.Response response) {
                mLoginView.removeSuccess(response.getResponse());
            }

            @Override
            public void onFailure() {
                mLoginView.removeError("Error removing user");
            }
        });
    }

    @Override
    public void putUsers(List<User> users) {
        AddAllUsers.Request request = new AddAllUsers.Request(users);
        mUseCaseHandler.execute(addAllUsers, request, new UseCase.UseCaseCallBack<AddAllUsers.Response>() {
            @Override
            public void onSuccess(AddAllUsers.Response response) {
                mLoginView.allUserPutSuccess(response.getResponse());
            }

            @Override
            public void onFailure() {
                mLoginView.allUserGetError("Error adding all users");
            }
        });
    }

    @Override
    public void getUsers() {
        GetAllUsers.Request request = new GetAllUsers.Request();
        mUseCaseHandler.execute(getAllUsers, request, new UseCase.UseCaseCallBack<GetAllUsers.Response>() {
            @Override
            public void onSuccess(GetAllUsers.Response response) {
                mLoginView.allUserGetSuccess(response.getmUserList());
            }

            @Override
            public void onFailure() {
                mLoginView.allUserGetError("Error getting all users");
            }
        });
    }

}
