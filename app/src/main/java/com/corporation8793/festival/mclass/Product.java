package com.corporation8793.festival.mclass;

public class Product {
    String productName;
    public String productPoint;

    public Product(String productName, String productPoint) {
        this.productName = productName;
        this.productPoint = productPoint;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPoint() {
        return productPoint;
    }

    public void setProductPoint(String productPoint) {
        this.productPoint = productPoint;
    }
}
