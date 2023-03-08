package com.example.shabnam.serverapp.Model;

/**
 * Created by Shabnam on 06/12/2018.
 */

public class Menu {
    int menuID;
    String menuTitle , menuType , menuImage;

    public Menu() {
    }

    public Menu(int menuID, String menuTitle, String menuType, String menuImage) {
        this.menuID = menuID;
        this.menuTitle = menuTitle;
        this.menuType = menuType;
        this.menuImage = menuImage;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }
}
