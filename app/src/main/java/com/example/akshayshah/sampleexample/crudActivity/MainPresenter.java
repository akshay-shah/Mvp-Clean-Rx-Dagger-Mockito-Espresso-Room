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
    public void addUser() {
        mRepository.putUser(new User(2, "shriram"), new DataSource.UserPutCallback() {
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
    public void removeUser() {
        mRepository.removeUser(new User(1, "akshay"), new DataSource.UserRemoveCallback() {
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
            public void OnAllUserPut() {
                mLoginView.allUserPutSuccess("Success Putting all users");
            }
        });
    }

    @Override
    public void getUsers() {
        mRepository.getAllUsers(new DataSource.UserLoadedCallback() {
            @Override
            public void OnUserLoaded(List<User> users) {
                mLoginView.allUserGetSuccess(users);
            }
        });
    }


}
