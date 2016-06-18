package com.easymeasure.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Clients")
public class Client extends ParseObject {

    public String getClientName() {
        return getString("client_name");
    }

    public void setClientName(String clientName) {
        put("client_name", clientName);
    }

    public String getClientGender() {
        return getString("client_gender");
    }

    public void setClientGender(String gender) {
        put("client_gender", gender);
    }

    public String getClientDeaultSize() {
        return getString("universal_size");
    }

    public void setClientSize(String value) {
        put("universal_size", value);
    }
}
