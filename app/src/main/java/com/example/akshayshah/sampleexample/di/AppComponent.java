package com.example.akshayshah.sampleexample.di;

import com.example.akshayshah.sampleexample.Login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by akshay.shah on 11/12/17.
 */

@Component(modules = {DataModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginActivity activity);
}
