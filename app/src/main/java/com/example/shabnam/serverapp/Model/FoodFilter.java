package com.example.shabnam.serverapp.Model;

import java.util.List;

/**
 * Created by Shabnam on 18/12/2018.
 */

public class FoodFilter {
    public int ID;
    public int ResID;
    public String FoodName;
    public String StartDate;
    public String EndDate;
    public String StartTime;
    public String EndTime;
    public String StartPrice;
    public String EndPrice;
    public String StartCount;
    public String EndCount;
    public List<Integer> lstType;

    public FoodFilter() {
    }

    public FoodFilter(int resID, String foodName, String startDate, String endDate, String startTime, String endTime, String startPrice, String endPrice, String startCount, String endCount, List<Integer> lstType) {
        ResID = resID;
        FoodName = foodName;
        StartDate = startDate;
        EndDate = endDate;
        StartTime = startTime;
        EndTime = endTime;
        StartPrice = startPrice;
        EndPrice = endPrice;
        StartCount = startCount;
        EndCount = endCount;
        this.lstType = lstType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getResID() {
        return ResID;
    }

    public void setResID(int resID) {
        ResID = resID;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getStartPrice() {
        return StartPrice;
    }

    public void setStartPrice(String startPrice) {
        StartPrice = startPrice;
    }

    public String getEndPrice() {
        return EndPrice;
    }

    public void setEndPrice(String endPrice) {
        EndPrice = endPrice;
    }

    public String getStartCount() {
        return StartCount;
    }

    public void setStartCount(String startCount) {
        StartCount = startCount;
    }

    public String getEndCount() {
        return EndCount;
    }

    public void setEndCount(String endCount) {
        EndCount = endCount;
    }

    public List<Integer> getLstType() {
        return lstType;
    }

    public void setLstType(List<Integer> lstType) {
        this.lstType = lstType;
    }
}
