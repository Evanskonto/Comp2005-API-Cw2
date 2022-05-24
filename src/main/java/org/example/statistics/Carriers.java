package org.example.statistics;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class Carriers {
    private List<String> names;
    private int totalCarriers;

    public Carriers(List<String> names, int totalCarriers) {
        this.names = names;
        this.totalCarriers = totalCarriers;
    }

    public List<String> getNames() {
        return names;
    }

    public int getTotalCarriers() {
        return totalCarriers;
    }

    public static Carriers fromJson(JSONObject object){
        String namesStr = object.getString("Names");
        List<String> names = Arrays.asList(namesStr.split(","));
        int totalCarriers = object.getInt("Total");
        return new Carriers(names, totalCarriers);
    }
}
