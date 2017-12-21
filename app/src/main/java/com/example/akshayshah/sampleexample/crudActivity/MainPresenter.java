package com.example.akshayshah.sampleexample.crudActivity;

import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataSource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by akshay.shah on 08/12/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mLoginView;
    private DataSource mRepository;

    @Inject
    public MainPresenter(MainContract.View view, DataSource mRepository) {
        mLoginView = view;
        this.mRepository = mRepository;
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
        mRepository.putAllusers(users, new DataSource.AllUserPutCallback() {
            @Override
            public void onAllUserPut() {
                mLoginView.allUserPutSuccess("Successfully Completed");
            }
        });
    }

    @Override
    public void getUsers() {
        mRepository.getAllUsers(new DataSource.UserLoadedCallback() {
            @Override
            public void onUserLoaded(List<User> users) {
                mLoginView.allUserGetSuccess(users);
            }
        });
    }


}
