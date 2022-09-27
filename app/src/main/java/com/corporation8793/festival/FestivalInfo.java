package com.corporation8793.festival;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FestivalInfo {
    @PrimaryKey(autoGenerate = true)
    public int fid;
    // 축제 이름
    @ColumnInfo(name = "festivalName")
    public String festivalName;
    // 축제 시작일
    @ColumnInfo(name = "festivalStart")
    public String festivalStart;
    // 축제 종료일
    @ColumnInfo(name = "festivalEnd")
    public String festivalEnd;
    // 축제 정보
    @ColumnInfo(name = "festivalCo")
    public String festivalCo;
    // 축제 개최 장소
    @ColumnInfo(name = "festivalLocation")
    public String festivalLocation;
    // 축제 주관기관
    @ColumnInfo(name = "festivalMnnst")
    public String festivalMnnst;
    // 축제 주최기관
    @ColumnInfo(name = "festivalAuspcInstt")
    public String festivalAuspcInstt;
    // 소재지도로명주소
    @ColumnInfo(name = "festivalRdnmadr")
    public String festivalRdnmadr;
    // 소재지지번주소
    @ColumnInfo(name = "festivalLnmadr")
    public String festivalLnmadr;
    // 위도
    @ColumnInfo(name = "festivalLatitude")
    public String festivalLatitude;
    // 경도
    @ColumnInfo(name = "festivalLongitude")
    public String festivalLongitude;


    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getFestivalStart() {
        return festivalStart;
    }

    public void setFestivalStart(String festivalStart) {
        this.festivalStart = festivalStart;
    }

    public String getFestivalEnd() {
        return festivalEnd;
    }

    public void setFestivalEnd(String festivalEnd) {
        this.festivalEnd = festivalEnd;
    }

    public String getFestivalCo() {
        return festivalCo;
    }

    public void setFestivalCo(String festivalCo) {
        this.festivalCo = festivalCo;
    }

    public String getFestivalLocation() {
        return festivalLocation;
    }

    public void setFestivalLocation(String festivalLocation) {
        this.festivalLocation = festivalLocation;
    }

    public String getFestivalMnnst() {
        return festivalMnnst;
    }

    public void setFestivalMnnst(String festivalMnnst) {
        this.festivalMnnst = festivalMnnst;
    }

    public String getFestivalAuspcInstt() {
        return festivalAuspcInstt;
    }

    public void setFestivalAuspcInstt(String festivalAuspcInstt) {
        this.festivalAuspcInstt = festivalAuspcInstt;
    }
    public String getFestivalRdnmadr() {
        return festivalRdnmadr;
    }

    public void setFestivalRdnmadr(String festivalRdnmadr) {
        this.festivalRdnmadr = festivalRdnmadr;
    }

    public String getFestivalLnmadr() {
        return festivalLnmadr;
    }

    public void setFestivalLnmadr(String festivalLnmadr) {
        this.festivalLnmadr = festivalLnmadr;
    }

    public String getFestivalLatitude() {
        return festivalLatitude;
    }

    public void setFestivalLatitude(String festivalLatitude) {
        this.festivalLatitude = festivalLatitude;
    }

    public String getFestivalLongitude() {
        return festivalLongitude;
    }

    public void setFestivalLongitude(String festivalLongitude) {
        this.festivalLongitude = festivalLongitude;
    }
}
