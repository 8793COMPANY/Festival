package com.corporation8793.festival.mclass;

public class Festival {
    String period, name;
    int image;

    public Festival(String period, String name, int image) {
        this.name = name;
        this.period = period;
        this.image = image;
    }

    public String getPeriod() {
        return period;
    }
    public String getName() {
        return name;
    }
    public int getImage() {
        return image;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImage(int image) {
        this.image = image;
    }
}
