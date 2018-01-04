package com.example.akshayshah.architecture.di;

import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.architecture.data.source.DataRepository;
import com.example.akshayshah.architecture.utils.schedulers.BaseSchedulerProvider;
import com.example.akshayshah.architecture.utils.schedulers.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by akshay.shah on 28/12/17.
 */

@Module(includes = DataModule.class)
public class UseCaseModule {

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
        return new GetAllUsers(dataRepository);
    }


}
