package com.easymeasure.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("BaseClothingMeasurements")
public class BaseClothingMeasurements extends ParseObject {

    public Client getClient(){ return (Client) getParseObject("client");}

    public void setClient(Client client){put("client", client);}

    public int getChest() {
        return getInt("chest");
    }

    public void setChest(int chest) {
        put("chest", chest);
    }

    public int getWaist() {
        return getInt("waist");
    }

    public void setWaist(int waist) {
        put("waist", waist);
    }

    public int getHip() {
        return getInt("hip");
    }

    public void setHip(int hip) {
        put("hip", hip);
    }

    public int getShoulder() {
        return getInt("shoulder");
    }

    public void setShoulder(int shoulder) {
        put("shoulder", shoulder);
    }

    public int getSleeve() {
        return getInt("sleeve");
    }

    public void setSleeve(int sleeve) {
        put("sleeve", sleeve);
    }
}
