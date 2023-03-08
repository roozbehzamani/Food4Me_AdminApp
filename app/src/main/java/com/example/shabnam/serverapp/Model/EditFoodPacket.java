package com.example.shabnam.serverapp.Model;

/**
 * Created by Roozbeh Zamani on 20/12/2018.
 */

public class EditFoodPacket {
    public int ID;
    public String packingDescription;
    public String packingImage;

    public EditFoodPacket() {
    }

    public EditFoodPacket(int ID, String packingDescription, String packingImage) {
        this.ID = ID;
        this.packingDescription = packingDescription;
        this.packingImage = packingImage;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPackingDescription() {
        return packingDescription;
    }

    public void setPackingDescription(String packingDescription) {
        this.packingDescription = packingDescription;
    }

    public String getPackingImage() {
        return packingImage;
    }

    public void setPackingImage(String packingImage) {
        this.packingImage = packingImage;
    }
}
