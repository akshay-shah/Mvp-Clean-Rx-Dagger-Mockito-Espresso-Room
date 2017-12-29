package com.example.akshayshah.sampleexample.crudActivity;

import com.example.akshayshah.sampleexample.UseCase;
import com.example.akshayshah.sampleexample.UseCaseHandler;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataSource;
import com.example.akshayshah.sampleexample.utils.schedulers.BaseSchedulerProvider;
import com.example.akshayshah.sampleexample.utils.schedulers.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        mLoginView = null;
        disposable.clear();
    }


    @Override
    public void addUser(User user) {
//        mRepository.putUser(user, new DataSource.UserPutCallback() {
//            @Override
//            public void onInsertSuccess() {
//                mLoginView.addSuccess("SuccessFull");
//            }
//
//            @Override
//            public void onInsertFail() {
//                mLoginView.addError("Failure");
//            }
//        });
        AddUser.Request request = new AddUser.Request(user);
        mUseCaseHandler.execute(addUser, request, new UseCase.UseCaseCallBack<AddUser.Response>() {
            @Override
            public void onSuccess(AddUser.Response response) {
                mLoginView.addSuccess(response.getResponse());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void removeUser(User user) {
//        mRepository.removeUser(user, new DataSource.UserRemoveCallback() {
//            @Override
//            public void onRemoveSuccess() {
//                mLoginView.removeSuccess("Success");
//            }
//
//            @Override
//            public void onRemoveFail() {
//                mLoginView.removeError("Error");
//            }
//        });
        RemoveUser.Request request = new RemoveUser.Request(user);
        mUseCaseHandler.execute(removeUser, request, new UseCase.UseCaseCallBack<RemoveUser.Response>() {
            @Override
            public void onSuccess(RemoveUser.Response response) {
                mLoginView.removeSuccess(response.getResponse());
            }

            @Override
            public void onFailure() {
                mLoginView.removeError("Error removing");
            }
        });
    }

    @Override
    public void putUsers(List<User> users) {
//        mRepository.putAllusers(users, () -> mLoginView.allUserPutSuccess("Successfully Completed"));
        AddAllUsers.Request request = new AddAllUsers.Request(users);
        mUseCaseHandler.execute(addAllUsers, request, new UseCase.UseCaseCallBack<AddAllUsers.Response>() {
            @Override
            public void onSuccess(AddAllUsers.Response response) {
                mLoginView.allUserPutSuccess(response.getResponse());
            }

            @Override
            public void onFailure() {

            }
        });
    }


    /**
     * Using RxJava to get users.
     */
    @Override
    public void getUsers() {
//        disposable.add(mRepository.getAllUsers()
//                .subscribeOn(schedulerProvider.io())
//                .observeOn(schedulerProvider.ui())
//                .subscribe(
//                        users -> {
//                            if (users.size() > 0) {
//                                mLoginView.allUserGetSuccess(users);
//                            } else {
//                                mLoginView.allUserGetError("No users Found");
//                            }
//                        },
//                        throwable -> mLoginView.allUserGetError("Error")
//                ));
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
