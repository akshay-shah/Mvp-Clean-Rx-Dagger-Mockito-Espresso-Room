package com.example.akshayshah.architecture.di;

import android.content.Context;

import com.example.akshayshah.architecture.data.source.DataRepository;
import com.example.akshayshah.architecture.data.source.local.LocalDataSource;
import com.example.akshayshah.architecture.data.source.local.UserDAO;
import com.example.akshayshah.architecture.data.source.local.UserDatabase;
import com.example.akshayshah.architecture.data.source.remote.RemoteDataSource;
import com.example.akshayshah.architecture.utils.AppExecutors;
import com.example.akshayshah.architecture.utils.schedulers.BaseSchedulerProvider;
import com.example.akshayshah.architecture.utils.schedulers.SchedulerProvider;

import javax.inject.Named;
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
    BaseSchedulerProvider providesScheduler() {
        return new SchedulerProvider();
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
    @Named("DataRepository")
    DataRepository providesDataRepository(LocalDataSource mLocalDataSource, RemoteDataSource mRemoteDataSource){
        return new DataRepository(mLocalDataSource,mRemoteDataSource);
    }



}
