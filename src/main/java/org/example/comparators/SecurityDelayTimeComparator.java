package org.example.comparators;

import org.example.AirportRecord;

import java.util.Comparator;

public class SecurityDelayTimeComparator implements Comparator<AirportRecord> {

    @Override
    //Order records by delay minutes from security issues
    public int compare(AirportRecord a1, AirportRecord a2) {
        int delays1 = a1.getDelayMinutes().getSecurity();
        int delays2 = a2.getDelayMinutes().getSecurity();
        return delays1 - delays2;
    }
}
