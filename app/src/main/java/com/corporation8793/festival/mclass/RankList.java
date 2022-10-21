package com.corporation8793.festival.mclass;

public class RankList {
    String mainText;
    public String subText;

    public RankList(String mainText, String subText) {
        this.mainText = mainText;
        this.subText = subText;
    }

    public String getMainText() {
        return mainText;
    }
    public String getSubText() {
        return  subText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }
    public void setSubText(String subText) {
        this.subText = subText;
    }
}
