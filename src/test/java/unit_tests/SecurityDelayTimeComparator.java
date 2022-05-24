package unit_tests;

import org.example.Airport;
import org.example.AirportRecord;
import org.example.statistics.DelayMinutes;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurityDelayTimeComparator {

    @Test
    public void testSortingWithComparator(){

        Comparator comparator = new org.example.comparators.SecurityDelayTimeComparator();

        //Setup for Record 1
        Airport mockAirport1 = new Airport("TA1","TestAirport1");
        int securityDelayMinutes1 = 1;
        DelayMinutes mockDelayMinutes1 = new DelayMinutes(0,0,0,
                securityDelayMinutes1,0, 1);

        //Setup for Record 2
        Airport mockAirport2 = new Airport("TA2","TestAirport2");
        int securityDelayMinutes2 = 2;
        DelayMinutes mockDelayMinutes2 = new DelayMinutes(0,0,0,
                securityDelayMinutes2,0, 2);

        //Setup for Record 3
        Airport mockAirport3 = new Airport("TA3","TestAirport3");
        int securityDelayMinutes3 = 3;
        DelayMinutes mockDelayMinutes3 = new DelayMinutes(0,0,0,
                securityDelayMinutes3,0, 3);


        List<AirportRecord> recordsToSort = new LinkedList<>();

        //The records 1, 2 and 3 are numbered by order of minutes
        AirportRecord record1 = new AirportRecord(mockAirport1, null, null, null, null, mockDelayMinutes1);
        AirportRecord record2 = new AirportRecord(mockAirport2, null, null, null, null, mockDelayMinutes2);
        AirportRecord record3 = new AirportRecord(mockAirport3, null, null, null, null, mockDelayMinutes3);

        //Set right carrier order
        recordsToSort.add(record1);
        recordsToSort.add(record2);
        recordsToSort.add(record3);

        //Test if records are ordered by carrier size
        Collections.sort(recordsToSort, comparator);
        for (int i = 0; i < recordsToSort.size()-1; i++) {
            boolean next_is_larger_or_equal =
                    recordsToSort.get(i+1).getDelayMinutes().getSecurity()
                            >= recordsToSort.get(i).getDelayMinutes().getSecurity();
            assertTrue(next_is_larger_or_equal);
        }

    }
}
