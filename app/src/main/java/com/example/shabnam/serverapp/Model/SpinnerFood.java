package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 03/12/2018.
 */

public class SpinnerFood {
    int ID;
    String foodName;

    public SpinnerFood() {
    }

    public SpinnerFood(int ID, String foodName) {
        this.ID = ID;
        this.foodName = foodName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
