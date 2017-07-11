package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */



public class Contact implements Serializable {

    public  String uid;
    public  String name;
    public  String email;
    public  String businessNumber;
    public  String primaryBusiness;
    public  String address;
    public  String province;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Constructor for creating a Contact object given all required values
     * */
    public Contact(String uid, String name, String email, String businessNumber){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.businessNumber = businessNumber;
    }

    /**
     * Constructor for creating a Contact object given all possible values
     * */
    public Contact(String uid, String name, String email, String businessNumber, String primaryBusiness, String address, String province){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.businessNumber = businessNumber;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("email", email);
        result.put("businessNumber", businessNumber);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);
        return result;
    }
}
