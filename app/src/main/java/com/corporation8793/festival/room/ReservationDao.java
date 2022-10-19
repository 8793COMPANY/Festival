package com.corporation8793.festival.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReservationDao {

    @Query("SELECT * FROM reservation")
    List<Reservation> getAllReservation();

    @Insert
    void insertReservation(Reservation reservation);

    @Delete
    void deleteReservation(Reservation reservation);

    @Update
    void updateReservation(Reservation reservation);
}
