package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 01/12/2018.
 */

public class User {
    int
            id           ;
    String
            email        ,
            password     ,
            access       ,
            description  ,
            name         ,
            image        ,
            ncode        ,
            mob_phone    ,
            home_phone   ,
            sex          ,
            home_address ,
            IMEI         ,
            activeCode   ;
    boolean
            enable       ,
            SMS_Enable   ;
    String
            birth_date   ,
            SNumber     ,
            Family;

    public User() {
    }

    public User(int id, String email, String password, String access, String description, String name, String image, String ncode, String mob_phone, String home_phone, String sex, String home_address, String IMEI, String activeCode, boolean enable, boolean SMS_Enable, String birth_date, String SNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.access = access;
        this.description = description;
        this.name = name;
        this.image = image;
        this.ncode = ncode;
        this.mob_phone = mob_phone;
        this.home_phone = home_phone;
        this.sex = sex;
        this.home_address = home_address;
        this.IMEI = IMEI;
        this.activeCode = activeCode;
        this.enable = enable;
        this.SMS_Enable = SMS_Enable;
        this.birth_date = birth_date;
        this.SNumber = SNumber;
    }

    public String getSNumber() {
        return SNumber;
    }

    public void setSNumber(String SNumber) {
        this.SNumber = SNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNcode() {
        return ncode;
    }

    public void setNcode(String ncode) {
        this.ncode = ncode;
    }

    public String getMob_phone() {
        return mob_phone;
    }

    public void setMob_phone(String mob_phone) {
        this.mob_phone = mob_phone;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isSMS_Enable() {
        return SMS_Enable;
    }

    public void setSMS_Enable(boolean SMS_Enable) {
        this.SMS_Enable = SMS_Enable;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }
}
