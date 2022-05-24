package unit_tests;

import org.example.Airport;
import org.example.AirportRecord;
import org.example.comparators.DelayCancelComparator;
import org.example.statistics.Flights;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DelayCancelComparatorTest {
    @Test
    public void testSortingWithComparator(){

        Comparator comparator = new DelayCancelComparator();

        //Setup for Record 1
        Airport mockAirport1 = new Airport("TA1","TestAirport1");
        int delayedFlights1 = 1;
        int cancelledFlights1 = 1;
        Flights mockFlights1 = new Flights(cancelledFlights1, delayedFlights1,0,
                0,2);

        //Setup for Record 2
        Airport mockAirport2 = new Airport("TA2","TestAirport2");
        int delayedFlights2 = 2;
        int cancelledFlights2 = 2;
        Flights mockFlights2 = new Flights(cancelledFlights2, delayedFlights2,0,
                0,4);

        //Setup for Record 3
        Airport mockAirport3 = new Airport("TA3","TestAirport3");
        int delayedFlights3 = 3;
        int cancelledFlights3 = 3;
        Flights mockFlights3 = new Flights(cancelledFlights3, delayedFlights3,0,
                0,4);


        List<AirportRecord> recordsToSort = new LinkedList<>();

        //The records 1, 2 and 3 are numbered by order of minutes
        AirportRecord record1 = new AirportRecord(mockAirport1, null, null, null, mockFlights1, null);
        AirportRecord record2 = new AirportRecord(mockAirport2, null, null, null, mockFlights2, null);
        AirportRecord record3 = new AirportRecord(mockAirport3, null, null, null, mockFlights3, null);

        //Set right carrier order
        recordsToSort.add(record1);
        recordsToSort.add(record2);
        recordsToSort.add(record3);

        //Test if records are ordered by carrier size
        Collections.sort(recordsToSort, comparator);
        for (int i = 0; i < recordsToSort.size()-1; i++) {
            boolean next_is_larger_or_equal =
                    recordsToSort.get(i+1).getFlights().getDelayed() +
                            recordsToSort.get(i+1).getFlights().getCancelled()
                            >= recordsToSort.get(i).getFlights().getDelayed() +
                            recordsToSort.get(i).getFlights().getDelayed();

            assertTrue(next_is_larger_or_equal);
        }

    }
}
