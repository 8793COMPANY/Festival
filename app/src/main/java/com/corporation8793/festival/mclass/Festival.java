package com.corporation8793.festival.mclass;

public class Festival {
    public String name;
    int image;

    public Festival(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setImage(int image) {
        this.image = image;
    }
}
