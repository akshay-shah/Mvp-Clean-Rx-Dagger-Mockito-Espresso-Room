package com.example.akshayshah.sampleexample.di;

import com.example.akshayshah.sampleexample.UseCase;
import com.example.akshayshah.sampleexample.UseCaseHandler;
import com.example.akshayshah.sampleexample.UseCaseScheduler;
import com.example.akshayshah.sampleexample.UseCaseThreadPoolScheduler;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.utils.schedulers.BaseSchedulerProvider;
import com.example.akshayshah.sampleexample.utils.schedulers.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by akshay.shah on 28/12/17.
 */

@Module(includes = DataModule.class)
public class UseCaseModule {


    @Inject
    BaseSchedulerProvider schedulerProvider;

    @Provides
    AddAllUsers providesAddAllUsers(@Named("DataRepository") DataRepository dataRepository) {
        return new AddAllUsers(dataRepository);
    }

    @Provides
    RemoveUser providesRemoveUser(@Named("DataRepository") DataRepository dataRepository) {
        return new RemoveUser(dataRepository);
    }

    @Provides
    AddUser providesAddUsers(@Named("DataRepository") DataRepository dataRepository) {
        return new AddUser(dataRepository);
    }

    @Provides
    GetAllUsers providesGetAllUsers(@Named("DataRepository") DataRepository dataRepository) {
        return new GetAllUsers(dataRepository, new SchedulerProvider());
    }

    @Provides
    UseCaseHandler providesUseCaseHandler(UseCaseScheduler scheduler) {
        return new UseCaseHandler(scheduler);
    }

    @Provides
    UseCaseScheduler providesUseCaseScheduler() {
        return new UseCaseThreadPoolScheduler();
    }
}
