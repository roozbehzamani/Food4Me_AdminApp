package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 01/12/2018.
 */

public class OrderChild {
    int ID;
    String OrderImage,OrderName,OrderCount;

    public OrderChild() {
    }

    public OrderChild(String orderImage, String orderName, String orderCount) {
        OrderImage = orderImage;
        OrderName = orderName;
        OrderCount = orderCount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrderImage() {
        return OrderImage;
    }

    public void setOrderImage(String orderImage) {
        OrderImage = orderImage;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public String getOrderCount() {
        return OrderCount;
    }

    public void setOrderCount(String orderCount) {
        OrderCount = orderCount;
    }
}
