package com.example.akshayshah.sampleexample.crudActivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.akshayshah.sampleexample.R;
import com.example.akshayshah.sampleexample.UseCaseHandler;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.sampleexample.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.sampleexample.data.User;
import com.example.akshayshah.sampleexample.data.source.DataRepository;
import com.example.akshayshah.sampleexample.di.AppComponent;
import com.example.akshayshah.sampleexample.di.DaggerAppComponent;
import com.example.akshayshah.sampleexample.di.DataModule;
import com.example.akshayshah.sampleexample.di.UseCaseModule;
import com.example.akshayshah.sampleexample.utils.schedulers.BaseSchedulerProvider;
import com.example.akshayshah.sampleexample.utils.schedulers.SchedulerProvider;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    private Button buttonAddUser, buttonRemoveUser, buttonGetAllUsers;
    private ListView listViewAllUsers;
    @Inject
    DataRepository mDataRepository;
    @Inject
    BaseSchedulerProvider schedulerProvider;
    @Inject
    RemoveUser removeUser;
    @Inject
    AddAllUsers addAllUsers;
    @Inject
    AddUser addUser;
    @Inject
    GetAllUsers getAllUsers;
    @Inject
    UseCaseHandler useCaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        //Initializing DaggerAppComponent with Activity Context
        AppComponent appComponent = DaggerAppComponent.builder().
                dataModule(new DataModule(this)).
                useCaseModule(new UseCaseModule()).
                build();
        appComponent.inject(this);

        presenter = new MainPresenter(this, mDataRepository, schedulerProvider, addUser, getAllUsers, removeUser, addAllUsers, useCaseHandler);
        final List<User> usersList = new ArrayList<>();
        usersList.add(new User(1, "akshay"));
        usersList.add(new User(2, "shriram"));
        usersList.add(new User(3, "piyush"));
        usersList.add(new User(4, "vikram"));
        buttonAddUser.setOnClickListener(v -> presenter.putUsers(usersList));
        buttonRemoveUser.setOnClickListener(v -> presenter.removeUser(new User(1, "akshay")));
        buttonGetAllUsers.setOnClickListener(v -> presenter.getUsers());
    }

    private void initViews() {
        buttonAddUser = findViewById(R.id.buttonAddUser);
        buttonRemoveUser = findViewById(R.id.buttonRemoveUser);
        buttonGetAllUsers = findViewById(R.id.buttonGetUser);
        listViewAllUsers = findViewById(R.id.listViewAllUsers);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
//        this.presenter = presenter;
    }


    @Override
    public void addError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeSuccess(String msg) {
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

    @SuppressWarnings("unchecked")
    @Override
    public void allUserGetSuccess(List<User> users) {
        List userStrings = new ArrayList();
        for (User u : users) {
            userStrings.add(u.getUserName());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userStrings);
        listViewAllUsers.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void allUserGetError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
