package com.easymeasure.model;

import com.parse.ParseClassName;

@ParseClassName("WomenMeasurements")
public class WomenMeasurements extends BaseClothingMeasurements {

    public int getSize() {
        return getInt("size");
    }

    public void setSize(int size) {
        put("size", size);
    }

    public int getBustPoint() {
        return getInt("bust_point");
    }

    public void setBustPoint(int bustPoint) {
        if(bustPoint == 0){
            switch(getSize()){
                case 32:
                    put("bust_point", 22);
                    break;
                case 34:
                    put("bust_point", 23);
                    break;
                case 36:
                    put("bust_point", 24);
                    break;
                case 38:
                    put("bust_point", 26);
                    break;
                case 40:
                    put("bust_point",  28);
                    break;
                case 42:
                    put("bust_point", 30);
                    break;
                case 44:
                    put("bust_point", 31);
                    break;
                case 46:
                    put("bust_point", 31);
                    break;
                case 48:
                    put("bust_point", 32);
                    break;
            }
        } else {
            put("bust_point", bustPoint);
        }
    }
}
