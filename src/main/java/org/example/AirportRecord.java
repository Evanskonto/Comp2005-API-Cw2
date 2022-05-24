package org.example;

import org.example.statistics.Carriers;
import org.example.statistics.DelayMinutes;
import org.example.statistics.Delays;
import org.example.statistics.Flights;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class AirportRecord {

    private Airport airport;
    private Time time;
    private Delays delays;
    private Carriers carriers;
    private Flights flights;
    private DelayMinutes delayMinutes;

    public AirportRecord(Airport airport, Time time, Delays delays, Carriers carriers, Flights flights, DelayMinutes delayMinutes) {
        this.airport = airport;
        this.time = time;
        this.delays = delays;
        this.carriers = carriers;
        this.flights = flights;
        this.delayMinutes = delayMinutes;
    }
    public Airport getAirport() {
        return airport;
    }

    public Time getTime() {
        return time;
    }

    public Delays getDelays() {
        return delays;
    }

    public Carriers getCarriers() {
        return carriers;
    }

    public Flights getFlights() {
        return flights;
    }

    public DelayMinutes getDelayMinutes() {
        return delayMinutes;
    }

    public static List<AirportRecord> fromJsonArray(JSONArray recordArray){
        List<AirportRecord> records = new LinkedList<>();
        for (Object record : recordArray){
            if(record instanceof JSONObject){
                records.add(AirportRecord.fromJson((JSONObject) record));
            }
        }
        return records;
    }

    public static AirportRecord fromJson(JSONObject record){
        Airport airport = Airport.fromJson(record.getJSONObject("Airport"));
        Time time = Time.fromJson(record.getJSONObject("Time"));

        JSONObject statistics = record.getJSONObject("Statistics");

        Delays delays = Delays.fromJson(statistics.getJSONObject("# of Delays"));
        Carriers carriers = Carriers.fromJson(statistics.getJSONObject("Carriers"));
        Flights flights = Flights.fromJson(statistics.getJSONObject("Flights"));
        DelayMinutes delayMinutes = DelayMinutes.fromJson(statistics.getJSONObject("Minutes Delayed"));

        return new AirportRecord(airport,time,delays, carriers,flights,delayMinutes);
    };
}
