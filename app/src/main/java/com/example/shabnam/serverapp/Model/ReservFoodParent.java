package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 01/12/2018.
 */

public class ReservFoodParent {
    public int ID;
    public String Time;
    public String Date;
    public String Price;
    public String Type;

    public ReservFoodParent() {
    }

    public ReservFoodParent(int ID, String time, String date, String price, String type) {
        this.ID = ID;
        Time = time;
        Date = date;
        Price = price;
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
