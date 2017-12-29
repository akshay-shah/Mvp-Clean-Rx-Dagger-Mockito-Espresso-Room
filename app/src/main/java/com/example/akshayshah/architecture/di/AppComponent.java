package com.example.akshayshah.architecture.di;

import com.example.akshayshah.architecture.crudActivity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by akshay.shah on 11/12/17.
 */

@Component(modules = {DataModule.class, UseCaseModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
}
