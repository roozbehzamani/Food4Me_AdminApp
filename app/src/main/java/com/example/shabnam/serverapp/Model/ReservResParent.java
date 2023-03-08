package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 01/12/2018.
 */

public class ReservResParent {
    public int ID;
    public String Time;
    public String Date;
    public String Price;
    public String TableNumber;

    public ReservResParent() {
    }

    public ReservResParent(int ID, String time, String date, String price, String tableNumber) {
        this.ID = ID;
        Time = time;
        Date = date;
        Price = price;
        TableNumber = tableNumber;
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

    public String getTableNumber() {
        return TableNumber;
    }

    public void setTableNumber(String tableNumber) {
        TableNumber = tableNumber;
    }
}
