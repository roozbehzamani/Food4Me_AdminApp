package com.example.shabnam.serverapp.Model;

import java.util.List;

/**
 * Created by Roozbeh Zamani on 20/12/2018.
 */

public class EditFood {
    public int ID;
    public String Name;
    public int MenuID;
    public String Price;
    public String Image;
    public String Recepi;
    public String CreateMaterial;
    public String BakingTime;
    public String UserEmail;
    public int ResID;

    public EditFood() {
    }

    public EditFood(int ID, String name, int menuID, String price, String image, String recepi, String createMaterial, String bakingTime, String userEmail, int resID) {
        this.ID = ID;
        Name = name;
        MenuID = menuID;
        Price = price;
        Image = image;
        Recepi = recepi;
        CreateMaterial = createMaterial;
        BakingTime = bakingTime;
        UserEmail = userEmail;
        ResID = resID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getMenuID() {
        return MenuID;
    }

    public void setMenuID(int menuID) {
        MenuID = menuID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getRecepi() {
        return Recepi;
    }

    public void setRecepi(String recepi) {
        Recepi = recepi;
    }

    public String getCreateMaterial() {
        return CreateMaterial;
    }

    public void setCreateMaterial(String createMaterial) {
        CreateMaterial = createMaterial;
    }

    public String getBakingTime() {
        return BakingTime;
    }

    public void setBakingTime(String bakingTime) {
        BakingTime = bakingTime;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public int getResID() {
        return ResID;
    }

    public void setResID(int resID) {
        ResID = resID;
    }
}
