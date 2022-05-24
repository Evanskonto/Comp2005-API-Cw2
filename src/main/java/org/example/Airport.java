package org.example;

import org.json.JSONObject;

public class Airport{
    private String code;
    private String name;

    public Airport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Airport fromJson(JSONObject object){
        String code = object.getString("Code");
        String name = object.getString("Name");
        return new Airport(code, name);
    }
}
