package com.corporation8793.festival;

public class Festival {
    String period, name;

    public Festival(String period, String name) {
        this.name = name;
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public String getName() {
        return name;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setName(String name) {
        this.name = name;
    }
}
