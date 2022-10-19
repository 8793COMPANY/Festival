package com.corporation8793.festival.mclass;

public class Booth {
    String boothName;
    String boothTime;
    public String boothPoint;
    public String saveResult;

    public Booth(String boothName, String boothTime, String boothPoint, String saveResult) {
        this.boothName = boothName;
        this.boothTime = boothTime;
        this.boothPoint = boothPoint;
        this.saveResult = saveResult;
    }

    public String getBoothName() {
        return boothName;
    }
    public String getBoothTime() {
        return boothTime;
    }
    public String getBoothPoint() {
        return boothPoint;
    }
    public String getSaveResult() {
        return saveResult;
    }

    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }
    public void setBoothTime(String boothTime) {
        this.boothTime = boothTime;
    }
    public void setBoothPoint(String boothPoint) {
        this.boothPoint = boothPoint;
    }
    public void setSaveResult(String saveResult) {
        this.saveResult = saveResult;
    }
}
