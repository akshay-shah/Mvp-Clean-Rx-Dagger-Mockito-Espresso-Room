package com.example.akshayshah.sampleexample.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.example.akshayshah.sampleexample.R;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.di.AppComponent;
import com.example.akshayshah.sampleexample.di.DaggerAppComponent;
import com.example.akshayshah.sampleexample.di.DataModule;


import java.util.ArrayList;
import java.util.List;

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
        List<User> usersList = new ArrayList<>();
        usersList.add(new User(1, "akshay"));
        usersList.add(new User(2, "shriram"));
        usersList.add(new User(3, "piyush"));
        usersList.add(new User(4, "vikram"));
        presenter.putUsers(usersList);
        presenter.getUsers();
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
    public void allUserPutSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void allUserPutError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
