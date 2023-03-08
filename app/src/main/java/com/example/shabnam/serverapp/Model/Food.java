package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 30/11/2018.
 */

public class Food {

    int
            id,
            menuID ,
            resID,
            FoodCount;

    boolean
            OrderType;

    String
            cost           ,
            name           ,
            User_Email     ,
            CreateMaterial ,
            foodImage      ,
            Recipe         ,
            bakingTime            ;

    public Food(int id, int menuID, int resID, String cost, String name, String user_Email, String createMaterial, String foodImage, String recipe, String bakingTime , int FoodCount , boolean OrderType) {
        this.id = id;
        this.menuID = menuID;
        this.resID = resID;
        this.cost = cost;
        this.name = name;
        User_Email = user_Email;
        CreateMaterial = createMaterial;
        this.foodImage = foodImage;
        Recipe = recipe;
        this.bakingTime = bakingTime;
        this.FoodCount = FoodCount;
        this.OrderType = OrderType;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        User_Email = user_Email;
    }

    public String getCreateMaterial() {
        return CreateMaterial;
    }

    public void setCreateMaterial(String createMaterial) {
        CreateMaterial = createMaterial;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getRecipe() {
        return Recipe;
    }

    public void setRecipe(String recipe) {
        Recipe = recipe;
    }

    public String getBakingTime() {
        return bakingTime;
    }

    public void setBakingTime(String bakingTime) {
        this.bakingTime = bakingTime;
    }

    public int getFoodCount() {
        return FoodCount;
    }

    public void setFoodCount(int foodCount) {
        FoodCount = foodCount;
    }

    public boolean isOrderType() {
        return OrderType;
    }

    public void setOrderType(boolean orderType) {
        OrderType = orderType;
    }
}
