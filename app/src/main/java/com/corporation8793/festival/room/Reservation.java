package com.corporation8793.festival.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "uid", onDelete = ForeignKey.CASCADE))
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    public int rid;
    //외래키
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
    //예약된 축제의 부스 > 이건 데이터가 현재 없음

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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
