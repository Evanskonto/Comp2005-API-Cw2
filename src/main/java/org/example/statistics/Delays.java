package org.example.statistics;

import org.json.JSONObject;

public class Delays {
    private int carrier;
    private int lateAircraft;
    private int nationalAviationSystem;
    private int security;
    private int weather;


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

    public int getTotalDelays(){
        return carrier + lateAircraft + nationalAviationSystem + security + weather;
    }

    public Delays(int carrier, int late_aircraft, int national_aviation_system, int security, int weather) {
        this.carrier = carrier;
        this.lateAircraft = late_aircraft;
        this.nationalAviationSystem = national_aviation_system;
        this.security = security;
        this.weather = weather;
    }

    public static Delays fromJson(JSONObject object){
        int carrier = object.getInt("Carrier");
        int lateAircraft = object.getInt("Late Aircraft");
        int nationalAviationSystem = object.getInt("National Aviation System");
        int security = object.getInt("Security");
        int weather = object.getInt("Weather");

        return new Delays(carrier, lateAircraft, nationalAviationSystem, security, weather);
    }
}
