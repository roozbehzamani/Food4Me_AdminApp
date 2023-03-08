package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 30/11/2018.
 */

public class Report {
    int ID;
    String
            FoodName          ,
            OrderType           ,
            Date           ,
            Time           ,
            FoodPrice          ,
            MenuType   ,
            OrderCount       ;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFoodNme() {
        return FoodName;
    }

    public void setFoodNme(String foodNme) {
        FoodName = foodNme;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
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

    public String getFoodPrice() {
        return FoodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        FoodPrice = foodPrice;
    }

    public String getMenuType() {
        return MenuType;
    }

    public void setMenuType(String menuType) {
        MenuType = menuType;
    }

    public String getOrderCount() {
        return OrderCount;
    }

    public void setOrderCount(String orderCount) {
        OrderCount = orderCount;
    }
}
