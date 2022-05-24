package org.example.statistics;

import org.json.JSONObject;

public class DelayMinutes {
    private int carrier;
    private int lateAircraft;
    private int nationalAviationSystem;
    private int security;
    private int weather;
    private int total;

    public int getCarrier() {
        return carrier;
    }

    public int getLateAircraft() {
        return lateAircraft;
    }

    public int getNationalAviationSystem() {
        return nationalAviationSystem;
    }

    public int getSecurity() {
        return security;
    }

    public int getWeather() {
        return weather;
    }

    public int getTotal() {
        return total;
    }

    public DelayMinutes(int carrier, int late_aircraft, int national_aviation_system, int security, int weather, int total) {
        this.carrier = carrier;
        this.lateAircraft = late_aircraft;
        this.nationalAviationSystem = national_aviation_system;
        this.security = security;
        this.weather = weather;
        this.total = total;
    }

    public static DelayMinutes fromJson(JSONObject object){
        int carrier = object.getInt("Carrier");
        int lateAircraft = object.getInt("Late Aircraft");
        int nationalAviationSystem = object.getInt("National Aviation System");
        int security = object.getInt("Security");
        int weather = object.getInt("Weather");
        int total = object.getInt("Total");
        return new DelayMinutes(carrier, lateAircraft, nationalAviationSystem, security, weather, total);
    }
}
