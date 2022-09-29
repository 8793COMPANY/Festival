package com.corporation8793.festival;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
/*
@Database(entities = {Reservation.class, User.class}, version = 1)
public abstract class AppDatabase3 extends RoomDatabase {

    public abstract ReservationDao reservationDao();

    private static AppDatabase3 INSTANCE;

    public static AppDatabase3 getDBInstance(Context context) {

        //INSTANCE가 null이면 초기화
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase3.class, "DB_NAME3").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }
}*/
