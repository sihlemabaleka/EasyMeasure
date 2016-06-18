package com.easymeasure.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("ClothesBedding")
public class ClothesBedding extends ParseObject {

    Boolean isClothing;

    public Boolean getClothing() {
        return getBoolean("is_clothing");
    }

    public void setClothing(Boolean value) {
        put("is_clothing", value);
    }

}
