package com.corporation8793.festival;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDBInstance(Context context) {

        //INSTANCE가 null이면 초기화
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_NAME").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }
}
