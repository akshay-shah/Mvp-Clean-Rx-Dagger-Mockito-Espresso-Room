package com.example.akshayshah.architecture.data.source;

import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.local.LocalDataSource;
import com.example.akshayshah.architecture.data.source.remote.RemoteDataSource;
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


    private DataRepository dataRepository;
    private User user;
    private List<User> userList = Lists.newArrayList(new User(1, "akshay"),
            new User(2, "shriram"),
            new User(3, "piyush"),
            new User(4, "vikram"));


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
        dataRepository.putUser(user);
        Mockito.verify(localDataSource).putUser(eq(user));
    }

    @Test
    public void removeUserTest() {
        dataRepository.removeUser(user);
        Mockito.verify(localDataSource).removeUser(eq(user));
    }

    @Test
    public void getAllUserTest() {
        dataRepository.getAllUsers();
        Mockito.verify(localDataSource).getAllUsers();
    }

    @Test
    public void putAllUsers() {
        dataRepository.putAllusers(userList);
        Mockito.verify(localDataSource).putAllusers(eq(userList));
    }
}
