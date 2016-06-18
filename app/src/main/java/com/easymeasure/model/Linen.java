package com.easymeasure.model;

import com.parse.ParseObject;

public class Linen extends ClothesBedding {

    public Client getClient(){ return (Client) getParseObject("client"); }

    public void setClient(Client client){put("client", client); }

    public String getType() {
        return getString("type");
    }

    public void setType(String type) {
        put("type", type);
    }

    public String getSize() {
        return getString("size");
    }

    public void setSize() {
        switch (getType()){
            case "single":
                    put("size", "single");
                break;
            case "doubleSingle":
                    put("size", "double single");
                break;
            case "QueenSize":
                    put("size", "queen size");
                break;
            case "kingSize":
                    put("size", "king size");
                break;

        }
    }
}
