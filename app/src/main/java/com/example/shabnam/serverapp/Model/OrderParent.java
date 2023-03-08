package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 01/12/2018.
 */

public class OrderParent {

    int ID;
    String OrderDate,OrderPrice,OrderTime,Status;

    public OrderParent() {
    }

    public OrderParent(int ID, String orderDate, String orderPrice, String orderTime, String status) {
        this.ID = ID;
        OrderDate = orderDate;
        OrderPrice = orderPrice;
        OrderTime = orderTime;
        Status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        OrderPrice = orderPrice;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }



    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
