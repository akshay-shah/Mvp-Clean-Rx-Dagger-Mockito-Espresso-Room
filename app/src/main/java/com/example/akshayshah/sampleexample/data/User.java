package com.example.akshayshah.sampleexample.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by akshay.shah on 08/12/17.
 */

@Entity(tableName = "Users")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userid")
    private final int userId;

    @NonNull
    @ColumnInfo(name = "username")
    private final String userName;


    public User(@NonNull int userId, @NonNull String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @NonNull
    public int getUserId() {
        return userId;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }
}
