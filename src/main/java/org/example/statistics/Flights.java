package org.example.statistics;

import org.json.JSONObject;

public class Flights {
    private int cancelled;
    private int delayed;
    private int diverted;
    private int onTime;
    private int total;

    public int getCancelled() {
        return cancelled;
    }

    public int getDelayed() {
        return delayed;
    }

    public int getDiverted() {
        return diverted;
    }

    public int getOnTime() {
        return onTime;
    }

    public int getTotal() {
        return total;
    }

    public Flights(int cancelled, int delayed, int diverted, int on_time, int total) {
        this.cancelled = cancelled;
        this.delayed = delayed;
        this.diverted = diverted;
        this.onTime = on_time;
        this.total = total;
    }

    public static Flights fromJson(JSONObject object){
        int cancelled = object.getInt("Cancelled");
        int delayed = object.getInt("Delayed");
        int diverted = object.getInt("Diverted");
        int onTime = object.getInt("On Time");
        int total = object.getInt("Total");

        return new Flights(cancelled, delayed, diverted, onTime, total);
    }
}
