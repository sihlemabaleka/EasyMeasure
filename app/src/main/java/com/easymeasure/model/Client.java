package com.easymeasure.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
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

    public ParseFile getClientProfilePicture() {
        return getParseFile("client_profile_picture");
    }

    public void setClientProfilePicture(ParseFile file) {
        put("client_profile_picture", file);
    }

    public int getClientOrderCount() {
        return getInt("client_order_count");
    }

    public void setClientOrderCount(int value) {
        put("client_order_count", value);
    }

    public int getClientPendingOrderCount() {
        return getInt("client_pending_order_count");
    }

    public void setClientPendingOrderCount(int value) {
        put("client_pending_order_count", value);
    }

}
