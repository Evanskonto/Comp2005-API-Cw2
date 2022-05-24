package org.example.comparators;

import org.example.AirportRecord;

import java.util.Comparator;

public class DelayCancelComparator implements Comparator<AirportRecord> {

    @Override
    //Order records by the sum of delays and flight cancels
    public int compare(AirportRecord a1, AirportRecord a2) {
        int delays1 = a1.getFlights().getDelayed();
        int delays2 = a2.getFlights().getDelayed();
        int cancels1 = a1.getFlights().getCancelled();
        int cancels2 = a1.getFlights().getCancelled();
        return (delays1 + cancels1) - (delays2 + cancels2);
    }
}
