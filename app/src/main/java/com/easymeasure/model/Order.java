package com.easymeasure.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Order")
public class Order extends ParseObject{

    public Client getClient() {
        return (Client) getParseObject("client");
    }

    public void setClient(Client client) {
        put("client", client);
    }

    public String getOrderType() {
        return getString("order_type");
    }

    public void setOrderType(String orderType) {
        put("order_type", orderType);
    }

    public void setTypeOfMake(ClothesBedding value){
        put("type_of_order", value);
    }

    public ClothesBedding getTypeOdMake(){
        return ((ClothesBedding) getParseObject("type_of_order"));
    }

    public ParseUser getCreatedBy(){
        return getParseUser("created_by");
    }

    public void setCreatedBy(ParseUser value){
        put("created_by", value);
    }

}
