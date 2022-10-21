package com.corporation8793.festival.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    public int rid;

    @ColumnInfo(name = "uid")
    public int uid;
    //사용자 아이디
    @ColumnInfo(name = "userId")
    public String userId;
    //예약한 축제 이름
    @ColumnInfo(name = "rFestival")
    public String rFestival;
    //예약 날짜
    @ColumnInfo(name = "rDate")
    public String rDate;
    //예약 인원
    @ColumnInfo(name = "rNum")
    public String rNum;
    //예약된 축제의 부스 > 현재 데이터 없음

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getrFestival() {
        return rFestival;
    }

    public void setrFestival(String rFestival) {
        this.rFestival = rFestival;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getrNum() {
        return rNum;
    }

    public void setrNum(String rNum) {
        this.rNum = rNum;
    }
}
