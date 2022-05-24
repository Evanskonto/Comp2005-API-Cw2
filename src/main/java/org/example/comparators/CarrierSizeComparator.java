package org.example.comparators;

import org.example.AirportRecord;

import java.util.Comparator;

public class CarrierSizeComparator implements Comparator<AirportRecord> {

    @Override
    //Order records by total carrier size
    public int compare(AirportRecord a1, AirportRecord a2) {
        int carriers1 = a1.getCarriers().getTotalCarriers();
        int carriers2 = a2.getCarriers().getTotalCarriers();
        return carriers1 - carriers2;
    }
}
