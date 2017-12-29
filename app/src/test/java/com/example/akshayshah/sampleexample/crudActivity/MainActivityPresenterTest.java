package com.example.akshayshah.sampleexample.crudActivity;

import android.support.annotation.NonNull;
import android.widget.Button;

import com.example.akshayshah.sampleexample.UseCase;
import com.example.akshayshah.sampleexample.UseCaseHandler;
import com.example.akshayshah.sampleexample.UseCaseScheduler;
import com.example.akshayshah.sampleexample.crudActivity.MainActivity;
import com.example.akshayshah.sampleexample.crudActivity.MainContract;
import com.example.akshayshah.sampleexample.crudActivity.MainPresenter;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.data.source.DataSource;
import com.example.akshayshah.sampleexample.utils.schedulers.BaseSchedulerProvider;
import com.example.akshayshah.sampleexample.utils.schedulers.ImmediateSchedulerProvider;
import com.example.akshayshah.sampleexample.utils.schedulers.SchedulerProvider;
import com.google.common.collect.Lists;

import org.bouncycastle.crypto.tls.PRFAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

import edu.emory.mathcs.backport.java.util.LinkedList;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
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

    //Captor is used to capture callbacks.
    @Captor
    private ArgumentCaptor<DataSource.UserListPutCallback> userPutCallbackCaptor;

    @Captor
    private ArgumentCaptor<DataSource.UserRemoveCallback> userRemoveCallBackCaptor;

    @Mock
    private MainContract.View view;

    private List<User> users = Lists.newArrayList(new User(1, "akshay"),
            new User(2, "shriram"),
            new User(3, "piyush"),
            new User(4, "vikram"));

    private BaseSchedulerProvider schedulerProvider;

    RemoveUser removeUser;

    AddAllUsers addAllUsers;

    AddUser addUser;

    GetAllUsers getAllUsers;

    UseCaseHandler useCaseHandler;

    @Before
    public void Test_buildActivity() {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new ImmediateSchedulerProvider();
        useCaseHandler = new UseCaseHandler(new TestUseCaseScheduler());
        removeUser = new RemoveUser(repository);
        addAllUsers = new AddAllUsers(repository);
        addUser = new AddUser(repository);
        getAllUsers = new GetAllUsers(repository, schedulerProvider);

    }

    @Test
    public void putUsersTest() {
        presenter = new MainPresenter(view, repository, null, addUser, getAllUsers, removeUser, addAllUsers, useCaseHandler);
        presenter.putUsers(users);
        Mockito.verify(repository).putAllusers(eq(users), userPutCallbackCaptor.capture());
        userPutCallbackCaptor.getValue().onAllUserPut();
        Mockito.verify(view).allUserPutSuccess("All users inserted");
    }

    @Test
    public void removeUserTest() {
        presenter = new MainPresenter(view, repository, null, addUser, getAllUsers, removeUser, addAllUsers, useCaseHandler);
        User user = new User(1, "akshay");
        presenter.removeUser(user);
        Mockito.verify(repository).removeUser(eq(user), userRemoveCallBackCaptor.capture());
        userRemoveCallBackCaptor.getValue().onRemoveSuccess();
        Mockito.verify(view).removeSuccess("Success");
    }

    @Test
    public void getUserSuccessTest() {
        presenter = new MainPresenter(view, repository, schedulerProvider, addUser, getAllUsers, removeUser, addAllUsers, useCaseHandler);
        Mockito.when(repository.getAllUsers()).thenReturn(Flowable.just(users));
        presenter.getUsers();
        Mockito.verify(view).allUserGetSuccess(eq(users));
    }

    @Test
    public void getUserErrorTest() {
        presenter = new MainPresenter(view, repository, schedulerProvider, addUser, getAllUsers, removeUser, addAllUsers, useCaseHandler);
        Mockito.when(repository.getAllUsers()).thenReturn(Flowable.error(new Exception()));
        presenter.getUsers();
        Mockito.verify(view).allUserGetError("Error getting all users");
    }


}
