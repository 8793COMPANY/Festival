package com.corporation8793.festival.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FestivalInfoDao {

    @Query("SELECT * FROM festivalinfo")
    List<FestivalInfo> getAllFestivalInfo();

    @Insert
    void insertFestivalInfo(FestivalInfo festivalInfo);

    @Delete
    void deleteFestivalInfo(FestivalInfo festivalInfo);

    @Update
    void updateFestivalInfo(FestivalInfo festivalInfo);

}
