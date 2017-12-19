package com.example.akshayshah.sampleexample.di;

import android.content.Context;

import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.data.source.local.LocalDataSource;
import com.example.akshayshah.sampleexample.data.source.local.UserDAO;
import com.example.akshayshah.sampleexample.data.source.local.UserDatabase;
import com.example.akshayshah.sampleexample.data.source.remote.RemoteDataSource;
import com.example.akshayshah.sampleexample.utils.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by akshay.shah on 11/12/17.
 */
@Module
public class DataModule {

    private Context mContext;

    public DataModule(Context context){
        mContext = context;
    }

    @Provides
    @Singleton
    AppExecutors providesAppExecutors() {
        return new AppExecutors();
    }

    @Provides
    @Singleton
    UserDatabase providesUserDatabase(){
        return UserDatabase.getInstance(mContext);
    }

    @Provides
    @Singleton
    UserDAO providesUserDao(UserDatabase mDatabase){
        return mDatabase.userDAO();
    }

    @Provides
    @Singleton
    RemoteDataSource provideRemoteDataSource(UserDAO mUserDao){
//        return RemoteDataSource.getInstance(mUserDao);
        return new RemoteDataSource(mUserDao);
    }

    @Provides
    @Singleton
    LocalDataSource providesLocalDataSource(UserDAO mUserDao, AppExecutors appExecutors) {
//        return LocalDataSource.getInstance(mUserDao);
        return new LocalDataSource(mUserDao, appExecutors);
    }

    @Provides
    @Singleton
    DataRepository providesDataRepository(LocalDataSource mLocalDataSource, RemoteDataSource mRemoteDataSource){
        return new DataRepository(mLocalDataSource,mRemoteDataSource);
    }



}
