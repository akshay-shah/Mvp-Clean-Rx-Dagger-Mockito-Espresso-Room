package com.example.akshayshah.sampleexample.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.example.akshayshah.sampleexample.R;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.di.AppComponent;
import com.example.akshayshah.sampleexample.di.DaggerAppComponent;
import com.example.akshayshah.sampleexample.di.DataModule;


import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;
    @Inject DataRepository mDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppComponent appComponent = DaggerAppComponent.builder().dataModule(new DataModule(this)).build();
        appComponent.inject(this);
        presenter = new LoginPresenter(this,mDataRepository);
        presenter.login();
        presenter.logout();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
//        this.presenter = presenter;
    }

    @Override
    public void loginError(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logoutSucces(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void logoutError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
