package com.example.akshayshah.architecture.crudActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.akshayshah.architecture.R;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.AddUser;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.GetAllUsers;
import com.example.akshayshah.architecture.crudActivity.domain.usecase.RemoveUser;
import com.example.akshayshah.architecture.data.User;
import com.example.akshayshah.architecture.data.source.DataRepository;
import com.example.akshayshah.architecture.di.AppComponent;
import com.example.akshayshah.architecture.di.DaggerAppComponent;
import com.example.akshayshah.architecture.di.DataModule;
import com.example.akshayshah.architecture.di.UseCaseModule;
import com.example.akshayshah.architecture.utils.schedulers.BaseSchedulerProvider;


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

        presenter = new MainPresenter(this, mDataRepository, schedulerProvider, addUser, getAllUsers, removeUser, addAllUsers);
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
