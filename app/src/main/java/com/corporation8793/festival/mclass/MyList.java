package com.corporation8793.festival.mclass;

public class MyList {
    String listName;
    int image1, image2;

    public MyList(String listName, int image1, int image2) {
        this.listName = listName;
        this.image1 = image1;
        this.image2 = image2;
    }

    public String getListName() {
        return listName;
    }
    public int getImage1() {
        return image1;
    }
    public int getImage2() {
        return image2;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
    public void setImage1(int image1) {
        this.image1 = image1;
    }
    public void setImage2(int image2) {
        this.image2 = image2;
    }
}
