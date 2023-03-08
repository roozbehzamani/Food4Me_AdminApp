package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam Moazam on 09/05/2018.
 */

public class Resturants {
    int
            ID,
            FoodAvragePrice;
    String
            resName ,
            resAddress ,
            resType ,
            resAvgServiceTime ,
            resLatLng ,
            resPhone ,
            resImage ,
            userEmail,
            ResBusinessLicense,
            SecendTime,
            FirstTime;

    float resPoints ;

    boolean resEnable,
            StudentRes,
            isGetOrder;

    public Resturants() {
    }

    public Resturants(int ID, String resName, String resAddress, String resType, String resAvgServiceTime, String resLatLng, String resPhone, String resImage, String userEmail, String resBusinessLicense, float resPoints, boolean resEnable, boolean studentRes , int FoodAvragePrice , String SecendTime , String FirstTime , boolean isGetOrder) {
        this.ID = ID;
        this.resName = resName;
        this.resAddress = resAddress;
        this.resType = resType;
        this.resAvgServiceTime = resAvgServiceTime;
        this.resLatLng = resLatLng;
        this.resPhone = resPhone;
        this.resImage = resImage;
        this.userEmail = userEmail;
        ResBusinessLicense = resBusinessLicense;
        this.resPoints = resPoints;
        this.resEnable = resEnable;
        StudentRes = studentRes;
        this.FirstTime = FirstTime;
        this.FoodAvragePrice = FoodAvragePrice;
        this.isGetOrder = isGetOrder;
        this.SecendTime = SecendTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResAddress() {
        return resAddress;
    }

    public void setResAddress(String resAddress) {
        this.resAddress = resAddress;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getResAvgServiceTime() {
        return resAvgServiceTime;
    }

    public void setResAvgServiceTime(String resAvgServiceTime) {
        this.resAvgServiceTime = resAvgServiceTime;
    }

    public String getResLatLng() {
        return resLatLng;
    }

    public void setResLatLng(String resLatLng) {
        this.resLatLng = resLatLng;
    }

    public String getResPhone() {
        return resPhone;
    }

    public void setResPhone(String resPhone) {
        this.resPhone = resPhone;
    }

    public String getResImage() {
        return resImage;
    }

    public void setResImage(String resImage) {
        this.resImage = resImage;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getResBusinessLicense() {
        return ResBusinessLicense;
    }

    public void setResBusinessLicense(String resBusinessLicense) {
        ResBusinessLicense = resBusinessLicense;
    }

    public float getResPoints() {
        return resPoints;
    }

    public void setResPoints(float resPoints) {
        this.resPoints = resPoints;
    }

    public boolean isResEnable() {
        return resEnable;
    }

    public void setResEnable(boolean resEnable) {
        this.resEnable = resEnable;
    }

    public boolean isStudentRes() {
        return StudentRes;
    }

    public void setStudentRes(boolean studentRes) {
        StudentRes = studentRes;
    }

    public int getFoodAvragePrice() {
        return FoodAvragePrice;
    }

    public void setFoodAvragePrice(int foodAvragePrice) {
        FoodAvragePrice = foodAvragePrice;
    }

    public String getSecendTime() {
        return SecendTime;
    }

    public void setSecendTime(String secendTime) {
        SecendTime = secendTime;
    }

    public String getFirstTime() {
        return FirstTime;
    }

    public void setFirstTime(String firstTime) {
        FirstTime = firstTime;
    }

    public boolean isGetOrder() {
        return isGetOrder;
    }

    public void setGetOrder(boolean getOrder) {
        isGetOrder = getOrder;
    }
}