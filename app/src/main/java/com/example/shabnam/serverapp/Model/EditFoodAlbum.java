package com.example.shabnam.serverapp.Model;

/**
 * Created by Roozbeh Zamani on 20/12/2018.
 */

public class EditFoodAlbum {
    public int ID;
    public String albumName;
    public String albumImage;

    public EditFoodAlbum() {
    }

    public EditFoodAlbum(int ID, String albumName, String albumImage) {
        this.ID = ID;
        this.albumName = albumName;
        this.albumImage = albumImage;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }
}
