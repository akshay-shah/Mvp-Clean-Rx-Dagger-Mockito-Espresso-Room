package com.example.akshayshah.sampleexample;

import android.widget.Button;

import com.example.akshayshah.sampleexample.crudActivity.MainActivity;
import com.example.akshayshah.sampleexample.crudActivity.MainContract;
import com.example.akshayshah.sampleexample.crudActivity.MainPresenter;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.data.source.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Created by akshay.shah on 19/12/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    private MainPresenter presenter;

    @Mock
    private DataRepository repository;

    @Captor
    private ArgumentCaptor<DataSource.UserLoadedCallback> userLoadedCallbackCaptor;

    @Captor
    private ArgumentCaptor<DataSource.AllUserPutCallback> userPutCallbackCaptor;

    @Captor
    private ArgumentCaptor<DataSource.UserRemoveCallback> userRemoveCallBackCaptor;

    @Mock
    private MainContract.View view;

    @Before
    public void Test_buildActivity() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void putUsersTest() {
        presenter = new MainPresenter(view, repository);
        List<User> usersList = new ArrayList<>();
        usersList.add(new User(1, "akshay"));
        usersList.add(new User(2, "shriram"));
        usersList.add(new User(3, "piyush"));
        usersList.add(new User(4, "vikram"));
        presenter.putUsers(usersList);
        Mockito.verify(repository).putAllusers(eq(usersList), userPutCallbackCaptor.capture());
        userPutCallbackCaptor.getValue().onAllUserPut();
        Mockito.verify(view).allUserPutSuccess("Successfully Completed");
    }

    @Test
    public void removeUserTest() {
        presenter = new MainPresenter(view, repository);
        User user = new User(1, "akshay");
        presenter.removeUser(user);
        Mockito.verify(repository).removeUser(eq(user), userRemoveCallBackCaptor.capture());
        userRemoveCallBackCaptor.getValue().onRemoveSuccess();
        Mockito.verify(view).removeSuccess("Success");
    }
}
