package com.example.akshayshah.sampleexample.crudActivity;

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

    @Inject
    public MainPresenter(MainContract.View view, DataSource mRepository, BaseSchedulerProvider schedulerProvider) {
        mLoginView = view;
        this.mRepository = mRepository;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        mLoginView = null;
    }


    @Override
    public void addUser(User user) {
        mRepository.putUser(user, new DataSource.UserPutCallback() {
            @Override
            public void onInsertSuccess() {
                mLoginView.addSuccess("SuccessFull");
            }

            @Override
            public void onInsertFail() {
                mLoginView.addError("Failure");
            }
        });
    }

    @Override
    public void removeUser(User user) {
        mRepository.removeUser(user, new DataSource.UserRemoveCallback() {
            @Override
            public void onRemoveSuccess() {
                mLoginView.removeSuccess("Success");
            }

            @Override
            public void onRemoveFail() {
                mLoginView.removeError("Error");
            }
        });
    }

    @Override
    public void putUsers(List<User> users) {
        mRepository.putAllusers(users, () -> mLoginView.allUserPutSuccess("Successfully Completed"));
    }


    /**
     * Using RxJava to get users.
     */
    @Override
    public void getUsers() {
        disposable.add(mRepository.getAllUsers()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        users -> {
                            if (users.size() > 0) {
                                mLoginView.allUserGetSuccess(users);
                            } else {
                                mLoginView.allUserGetError("No users Found");
                            }
                        },
                        throwable -> mLoginView.allUserGetError("Error")
                ));
    }

}
