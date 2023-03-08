package com.example.shabnam.serverapp.Model;

import java.util.Date;

/**
 * Created by Roozbeh Zamani on 24/01/2019.
 */

public class AdminFilterDM {
    public Date DateFrom;
    public Date DateTo;
    public int CountFrom;
    public int CountTo;
    public String FoodName;
    public int ResID;

    public AdminFilterDM() {
    }

    public AdminFilterDM(Date dateFrom, Date dateTo, int countFrom, int countTo, String foodName, int resID) {
        DateFrom = dateFrom;
        DateTo = dateTo;
        CountFrom = countFrom;
        CountTo = countTo;
        FoodName = foodName;
        ResID = resID;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date dateTo) {
        DateTo = dateTo;
    }

    public int getCountFrom() {
        return CountFrom;
    }

    public void setCountFrom(int countFrom) {
        CountFrom = countFrom;
    }

    public int getCountTo() {
        return CountTo;
    }

    public void setCountTo(int countTo) {
        CountTo = countTo;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public int getResID() {
        return ResID;
    }

    public void setResID(int resID) {
        ResID = resID;
    }
}
