package org.example;


import org.json.JSONObject;

import java.time.Month;

public class Time {
    private String label;
    private int month;
    private String monthName;
    private int year;

    public Time(String label, int month, String month_name, int year) {
        this.label = label;
        this.month = month;
        this.monthName = month_name;
        this.year = year;
    }

    public String getLabel() {
        return label;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthName() {
        return monthName;
    }

    public int getYear() {
        return year;
    }

    public static Time fromJson(JSONObject object){
        String label = object.getString("Label");
        int month = object.getInt("Month");
        String monthName = object.getString("Month Name");
        int year = object.getInt("Year");
        return new Time(label,month,monthName, year);
    }
}
