package com.example.akshayshah.architecture.crudActivity;

import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataRepository;
import com.example.akshayshah.architecture.data.source.DataSource;
import com.example.akshayshah.architecture.utils.schedulers.BaseSchedulerProvider;
import com.example.akshayshah.architecture.utils.schedulers.ImmediateSchedulerProvider;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Created by akshay.shah on 19/12/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    @Mock
    private DataRepository repository;
    @Mock
    private MainContract.View view;
    private List<User> users = Lists.newArrayList(new User(1, "akshay"),
            new User(2, "shriram"),
            new User(3, "piyush"),
            new User(4, "vikram"));
    private BaseSchedulerProvider schedulerProvider;
    private RemoveUser removeUser;
    private AddAllUsers addAllUsers;
    private AddUser addUser;
    private GetAllUsers getAllUsers;
    private MainPresenter presenter;

    @Before
    public void Test_buildActivity() {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new ImmediateSchedulerProvider();
        removeUser = new RemoveUser(repository);
        addAllUsers = new AddAllUsers(repository);
        addUser = new AddUser(repository);
        getAllUsers = new GetAllUsers(repository);
        presenter = new MainPresenter(view, repository, schedulerProvider, addUser, getAllUsers, removeUser, addAllUsers);

    }

    @Test
    public void putUsersTest() {
        Mockito.when(repository.putAllusers(users)).thenReturn(Observable.just(new AddAllUsers.Response("Success inserting " + users.size())));
        presenter.putUsers(users);
        Mockito.verify(view).allUserPutSuccess("Success inserting " + users.size());
    }

    @Test
    public void removeUserTest() {
        User user = new User(1, "akshay");
        Mockito.when(repository.removeUser(user)).thenReturn(Single.just(new RemoveUser.Response("Deleted 1")));
        presenter.removeUser(user);
        Mockito.verify(view).removeSuccess("Deleted 1");
    }

    @Test
    public void getUserSuccessTest() {
        Mockito.when(repository.getAllUsers()).thenReturn(Flowable.just(new GetAllUsers.Response(users)));
        presenter.getUsers();
        Mockito.verify(view).allUserGetSuccess(eq(users));
    }

    @Test
    public void getUserErrorTest() {
        Mockito.when(repository.getAllUsers()).thenReturn(Flowable.error(new Exception()));
        presenter.getUsers();
        Mockito.verify(view).allUserGetError("Error getting users");
    }


}
