package com.example.akshayshah.sampleexample.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.akshayshah.sampleexample.data.User;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by akshay.shah on 08/12/17.
 */

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase INSTANCE;

    public abstract UserDAO userDAO();

    private static final Object sLock = new Object();

    public static UserDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        UserDatabase.class, "UserDatabase.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
