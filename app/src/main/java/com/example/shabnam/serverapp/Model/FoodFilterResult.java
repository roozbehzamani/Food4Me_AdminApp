package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 18/12/2018.
 */

public class FoodFilterResult {
    public int ID;
    public String FoodName;
    public String Date;
    public String Time;
    public String Price;
    public String Count;
    public int Type;

    public FoodFilterResult() {
    }

    public FoodFilterResult(String foodName, String date, String time, String price, String count, int type) {
        FoodName = foodName;
        Date = date;
        Time = time;
        Price = price;
        Count = count;
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
