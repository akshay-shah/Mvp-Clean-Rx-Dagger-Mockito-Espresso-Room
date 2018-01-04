package com.example.akshayshah.architecture.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.akshayshah.architecture.data.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by akshay.shah on 08/12/17.
 */
@Dao
public interface UserDAO {

    //Returning Flowable object via Room Database
    @Query("SELECT * FROM Users")
    List<User> getUser();

    @Query("SELECT * FROM Users where UserId = :id")
    User getUserbyId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long putUser(User user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    @Query("DELETE FROM Users where UserId = :userId")
    int deleteUser(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> putAllUsers(List<User> users);
}
