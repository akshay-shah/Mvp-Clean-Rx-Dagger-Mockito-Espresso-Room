package com.example.akshayshah.sampleexample.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.WorkerThread;

import com.example.akshayshah.sampleexample.data.User;

import java.util.List;

import javax.annotation.concurrent.ThreadSafe;

import io.reactivex.Flowable;

/**
 * Created by akshay.shah on 08/12/17.
 */
@Dao
public interface UserDAO {

    //Returning Flowable object via Room Database
    @Query("SELECT * FROM Users")
    Flowable<List<User>> getUser();

    @Query("SELECT * FROM Users where UserId = :id")
    User getUserbyId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void putUser(User user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    @Query("DELETE FROM Users where UserId = :userId")
    void deleteUser(int userId);
}
