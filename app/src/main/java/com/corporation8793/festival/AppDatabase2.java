package com.corporation8793.festival;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FestivalInfo.class}, version = 1)
public abstract class AppDatabase2 extends RoomDatabase {

    public abstract FestivalInfoDao festivalInfoDao();

    private static AppDatabase2 INSTANCE;

    public static AppDatabase2 getDBInstance(Context context) {

        //INSTANCE가 null이면 초기화
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase2.class, "DB_NAME2").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }
}
