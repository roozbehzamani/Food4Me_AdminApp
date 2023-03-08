package com.example.shabnam.serverapp.Common;

import com.example.shabnam.serverapp.Model.Food;
import com.example.shabnam.serverapp.Model.Resturants;
import com.example.shabnam.serverapp.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shabnam on 30/11/2018.
 */

public class Common {
    public static Food selectedFoodForEdit;
    public static User currentUser;
    public static Resturants currentResturants;
    public static ArrayList<Food> foods ;
    public static ArrayList<Integer> foodIDs = new ArrayList<>();
    public static int counterForSub = 0;
    public static int PositionForSub = 0;
    public static int PackagePrice = 0;
    public static String ParentIDForFlowerCart ;


    public static final String USER_KEY = "User";
    public static final String PWD_KEY = "Passwoed";

}
