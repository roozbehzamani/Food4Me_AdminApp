package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 01/12/2018.
 */

public class ReservResChild {
    public int ID;
    public String FoodName;
    public String FoodCount;
    public String FoodImage;

    public ReservResChild() {
    }

    public ReservResChild(int ID, String foodName, String foodCount, String foodImage) {
        this.ID = ID;
        FoodName = foodName;
        FoodCount = foodCount;
        FoodImage = foodImage;
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

    public String getFoodCount() {
        return FoodCount;
    }

    public void setFoodCount(String foodCount) {
        FoodCount = foodCount;
    }

    public String getFoodImage() {
        return FoodImage;
    }

    public void setFoodImage(String foodImage) {
        FoodImage = foodImage;
    }
}
