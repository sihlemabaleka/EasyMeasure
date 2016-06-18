package com.easymeasure.model;

public class KidsMeasurement extends BaseClothingMeasurements {


    public String getAgeGroup() {
        return getString("age_group");
    }

    public void setAgeGroup(String ageGroup) {
        put("age_group", ageGroup);
    }

    public String getGender() {
        return getString("gender");
    }

    public void setGender(String value) {
        put("gender", value);
    }

    public int getHeight() {
        return getInt("height");
    }

    public void setHeight(int value) {
        if (value == 0) {
            switch (getAgeGroup()) {
                case "3 to 5":
                    if (!getGender().equals("boy")) {
                        put("height", 107);
                    } else {
                        put("height", 107);
                    }
                    break;
                case "5 to 7":
                    if (!getGender().equals("boy")) {
                        put("height", 120.5);
                    } else {
                        put("height", 119);
                    }
                    break;
                case "7 to 9":
                    if (!getGender().equals("boy")) {
                        put("height", 133.5);
                    } else {
                        put("height", 127);
                    }
                    break;
                case "9 - 11":
                    if (!getGender().equals("boy")) {
                        put("height", 145);
                    } else {
                        put("height", 137);
                    }
                    break;
            }
        } else {
            put("height", value);
        }
    }

    public int getWidthBack() {
        return getInt("width_of_back");
    }

    public void setWidthBack(int value) {
        if (value == 0) {
            switch (getAgeGroup()) {
                case "3 to 5":
                    put("width_of_back", 24.5);
                    break;
                case "5 to 7":
                    put("width_of_back", 27.5);
                    break;
                case "7 to 9":
                    put("width_of_back", 29.5);
                    break;
                case "9 - 11":
                    put("width_of_back", 31.5);
            }
        } else {
            put("width_of_back", value);
        }
    }

    public int getWidthChest() {
        return getInt("width_of_chest");
    }

    public void setWidthChest(int value) {
        if (value == 0) {
            switch (getAgeGroup()) {
                case "3 to 5":
                    put("width_of_chest", 24);
                    break;
                case "5 to 7":
                    put("width_of_chest", 27);
                    break;
                case "7 to 9":
                    put("width_of_chest", 29);
                    break;
                case "9 - 11":
                    put("width_of_chest", 31);
            }
        } else {
            put("width_of_chest", value);
        }
    }

    public int getCufflingShortSleeve() {

        return getInt("short_sleeve_cuffling_length");
    }

    public void setCufflingShortSleeve(int value) {
        if (value == 0) {
            switch (getAgeGroup()) {
                case "3 to 5":
                    put("short_sleeve_cuffling_length", 20);
                    break;
                case "5 to 7":
                    put("short_sleeve_cuffling_length", 21.5);
                    break;
                case "7 to 9":
                    put("short_sleeve_cuffling_length", 24);
                    break;
                case "9 - 11":
                    put("short_sleeve_cuffling_length", 25);
            }
        } else {
            put("short_sleeve_cuffling_length", value);
        }
    }

    public int getCufflingLongSleeve() {
        return getInt("long_sleeve_cuffling_length");
    }

    public void setCufflingLongSleeve(int value) {
        if (value == 0) {
            switch (getAgeGroup()) {
                case "3 to 5":
                    put("long_sleeve_cuffling_length", 14);
                    break;
                case "5 to 7":
                    put("long_sleeve_cuffling_length", 14.5);
                    break;
                case "7 to 9":
                    put("long_sleeve_cuffling_length", 15);
                    break;
                case "9 - 11":
                    put("long_sleeve_cuffling_length", 26);
            }
        } else {
            put("long_sleeve_cuffling_length", value);
        }
    }

    public int getWaistToGroundHeight() {
        return getInt("waist_to_ground_length");
    }

    public void setWaistToGroundHeight(int value) {
        if (value == 0) {
            switch (getAgeGroup()) {
                case "3 to 5":
                    if (!getGender().equals("boy")) {
                        put("waist_to_ground_length", 62.5);
                    } else {
                        put("waist_to_ground_length", 68.5);
                    }
                    break;
                case "5 to 7":
                    if (!getGender().equals("boy")) {
                        put("waist_to_ground_length", 77);
                    } else {
                        put("waist_to_ground_length", 73.5);
                    }
                    break;
                case "7 to 9":
                    if (!getGender().equals("boy")) {
                        put("waist_to_ground_length", 85);
                    } else {
                        put("waist_to_ground_length", 78.5);
                    }
                    break;
                case "9 - 11":
                    if (!getGender().equals("boy")) {
                        put("waist_to_ground_length", 90);
                    } else {
                        put("waist_to_ground_length", 83.5);
                    }
                    break;
            }
        } else {
            put("waist_to_ground_length", value);
        }
    }

    public int getCasualShirtLength() {
        return getInt("casual_shirt_length");
    }

    public void setCasualShirtLength(int value) {
        if (value == 0) {
            switch (getAgeGroup()) {
                case "3 to 5":
                    if (!getGender().equals("boy")) {
                        put("casual_shirt_length", 20);
                    } else {
                        put("casual_shirt_length", 40.5);
                    }
                    break;
                case "5 to 7":
                    if (!getGender().equals("boy")) {
                        put("casual_shirt_length", 25);
                    } else {
                        put("casual_shirt_length", 45.5);
                    }
                    break;
                case "7 to 9":
                    if (!getGender().equals("boy")) {
                        put("casual_shirt_length", 30);
                    } else {
                        put("casual_shirt_length", 50.5);
                    }
                    break;
                case "9 - 11":
                    if (!getGender().equals("boy")) {
                        put("casual_shirt_length", 35);
                    } else {
                        put("casual_shirt_length", 56);
                    }
                    break;
            }
        } else {
            put("casual_shirt_length", value);
        }
    }

    public int getNeckSize() {
        return getInt("neck_size_length");
    }

    public void setNeckSize(int value) {
        if (value == 0) {
            switch (getAgeGroup()) {
                case "3 to 5":
                    if (!getGender().equals("boy")) {
                        put("neck_size_length", 0);
                    } else {
                        put("neck_size_length", 28);
                    }
                    break;
                case "5 to 7":
                    if (!getGender().equals("boy")) {
                        put("neck_size_length", 0);
                    } else {
                        put("neck_size_length", 29);
                    }
                    break;
                case "7 to 9":
                    if (!getGender().equals("boy")) {
                        put("neck_size_length", 0);
                    } else {
                        put("neck_size_length", 31);
                    }
                    break;
                case "9 - 11":
                    if (!getGender().equals("boy")) {
                        put("neck_size_length", 0);
                    } else {
                        put("neck_size_length", 33);
                    }
                    break;
            }
        } else {
            put("neck_size_length", value);
        }
    }

    public int getTrouserBottoms() {
        return getInt("trouser_bottoms_length");
    }

    public void setTrouserBottoms(int value) {
        if (value == 0) {
            switch (getAgeGroup()) {
                case "3 to 5":
                    if (!getGender().equals("boy")) {
                        put("trouser_bottoms_length", 24.5);
                    } else {
                        put("trouser_bottoms_length", 32.5);
                    }
                    break;
                case "5 to 7":
                    if (!getGender().equals("boy")) {
                        put("trouser_bottoms_length", 26);
                    } else {
                        put("trouser_bottoms_length", 34);
                    }
                    break;
                case "7 to 9":
                    if (!getGender().equals("boy")) {
                        put("trouser_bottoms_length", 28.5);
                    } else {
                        put("trouser_bottoms_length", 36.5);
                    }
                    break;
                case "9 - 11":
                    if (!getGender().equals("boy")) {
                        put("trouser_bottoms_length", 30);
                    } else {
                        put("trouser_bottoms_length", 39.5);
                    }
                    break;
            }
        } else {
            put("trouser_bottoms_length", value);
        }
    }
}
