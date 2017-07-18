package com.example.hoaht.roomexample.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.hoaht.roomexample.databases.models.User;
import com.example.hoaht.roomexample.databases.models.UserDao;

/**
 * AppDatabase.
 *
 * @author HoaHT
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
