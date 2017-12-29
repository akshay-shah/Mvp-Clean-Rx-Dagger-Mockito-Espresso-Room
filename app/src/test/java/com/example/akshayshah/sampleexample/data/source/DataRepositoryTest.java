package com.example.akshayshah.sampleexample.data.source;

import com.example.akshayshah.sampleexample.UseCaseHandler;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.local.LocalDataSource;
import com.example.akshayshah.sampleexample.data.source.remote.RemoteDataSource;
import com.google.common.collect.Lists;

import org.junit.Assert;
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

import javax.inject.Inject;

import dagger.Module;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Created by akshay.shah on 26/12/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class DataRepositoryTest {
    @Mock
    private LocalDataSource localDataSource;
    @Mock
    private RemoteDataSource remoteDataSource;
    @Mock
    private DataSource.UserPutCallback userPutCallback;
    @Mock
    private DataSource.UserRemoveCallback userRemoveCallback;
    @Mock
    private DataSource.UserListPutCallback userListPutCallback;


    private DataRepository dataRepository;
    private User user;
    private List<User> userList = Lists.newArrayList(new User(1, "akshay"),
            new User(2, "shriram"),
            new User(3, "piyush"),
            new User(4, "vikram"));

    @Captor
    private ArgumentCaptor<DataSource.UserPutCallback> userPutCaptor;
    @Captor
    private ArgumentCaptor<DataSource.UserRemoveCallback> userRemoveCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dataRepository = new DataRepository(localDataSource, remoteDataSource);
        user = new User(1, "baburao");
    }

    @Test
    public void checkNotNullDataRespository() {
        Assert.assertNotNull(dataRepository);
    }

    @Test
    public void putUserTest() {
        dataRepository.putUser(user, userPutCallback);
        Mockito.verify(localDataSource).putUser(eq(user), any(DataSource.UserPutCallback.class));
        Mockito.verify(remoteDataSource).putUser(eq(user), any(DataSource.UserPutCallback.class));
    }

    @Test
    public void removeUserTest() {
        dataRepository.removeUser(user, userRemoveCallback);
        Mockito.verify(localDataSource).removeUser(eq(user), any(DataSource.UserRemoveCallback.class));
        Mockito.verify(remoteDataSource).removeUser(eq(user), any(DataSource.UserRemoveCallback.class));
    }

    @Test
    public void getAllUserTest() {
        dataRepository.getAllUsers();
        Mockito.verify(localDataSource).getAllUsers();
    }

    @Test
    public void putAllUsers() {
        dataRepository.putAllusers(userList, userListPutCallback);
        Mockito.verify(localDataSource).putAllusers(eq(userList), any(DataSource.UserListPutCallback.class));
    }
}
