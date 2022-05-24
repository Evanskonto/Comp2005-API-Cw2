package org.example.filters;

import org.example.AirportRecord;
import org.example.comparators.DelayCancelComparator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RecordFilter {


    public static List<AirportRecord> filterByYear(List<AirportRecord> records, int year){

        List<AirportRecord> filteredRecords = new LinkedList<>();
        for(AirportRecord record : records){
                if(record.getTime().getYear() == year) {
                    filteredRecords.add(record);
                }
        }
        return filteredRecords;

    }

    public static List<AirportRecord> filterByAirport(List<AirportRecord> records, String airportCode){

        List<AirportRecord> filteredRecords = new LinkedList<>();
        for(AirportRecord record : records){
                if(record.getAirport().getCode().equals(airportCode)) {
                    filteredRecords.add(record);
                }
        }
        return filteredRecords;
    }

    public static List<AirportRecord> filterByAirportBetweenYears(List<AirportRecord> records, String airportCode, int minYear, int maxYear){

        List<AirportRecord> filteredRecords = new LinkedList<>();
        for(AirportRecord record : records)
            //Filter by years
            if(record.getTime().getYear() >= minYear && record.getTime().getYear() <= maxYear)
                //Filter by airport
                if(record.getAirport().getCode().equals(airportCode))
                    filteredRecords.add(record);

        return filteredRecords;
    }

    public static List<AirportRecord> filterByYear(List<AirportRecord> records, int year, int weatherDelayThreshold){

        List<AirportRecord> filteredRecords = new LinkedList<>();
        for(AirportRecord record : records)
            //Filter by year
            if(record.getTime().getYear() == year)
                //Filter by threshold
                if(record.getDelays().getWeather() < weatherDelayThreshold)
                    filteredRecords.add(record);

        return filteredRecords;
    }



}
