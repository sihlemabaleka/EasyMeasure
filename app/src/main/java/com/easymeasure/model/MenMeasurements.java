package com.easymeasure.model;

public class MenMeasurements extends BaseClothingMeasurements {

    public int getSize() {
        return getInt("size");
    }

    public void setSize(int size) {
        put("size", size);
    }

    public double getTrouserLength() {
        return getInt("trouser_length");
    }

    public void setTrouserLength(double trouserLength) {
        if(trouserLength == 0){
            switch(getSize()){
                case 32:
                    put("trouser_length", 97.5);
                    break;
                case 34:
                    put("trouser_length", 98);
                    break;
                case 36:
                    put("trouser_length", 100.5);
                    break;
                case 38:
                    put("trouser_length", 103);
                    break;
                case 40:
                    put("trouser_length",  104);
                    break;
                case 42:
                    put("trouser_length", 106);
                    break;
                case 44:
                    put("trouser_length", 107.5);
                    break;
                case 46:
                    put("trouser_length", 109);
                    break;
                case 48:
                    put("trouser_length", 110.5);
                    break;
            }
        } else {
            put("trouser_length", trouserLength);
        }
    }
}
