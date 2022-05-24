package integration_tests;

import org.example.APIClient;
import org.example.AirportRecord;
import org.example.filters.RecordFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterAPIResults {

    static APIClient client;
    static List<AirportRecord> records;

    @BeforeAll
    public static void fetchRecords(){
        //Fetch only once for performance
        client = new APIClient();
        try {
            records = client.fetchAirportRecords();
        } catch (Exception e){
            fail("Failed API Access");
        }
    }

    @Test
    public void testAPIFilteringByAirport() {
        String[] testedAirportCodes = {"ATL", "BOS", "BWI", "DCA", "EWR", "IAD", "LAX"};

        boolean hasOnceResults = false;
        for(String testedAirportCode : testedAirportCodes){
            List<AirportRecord> filteredResults = RecordFilter.filterByAirport(records, testedAirportCode);

            if(!filteredResults.isEmpty())
                hasOnceResults = true;

            for(AirportRecord record: filteredResults){
                assertEquals(record.getAirport().getCode(), testedAirportCode);
            }
        }

        assertTrue(hasOnceResults);
    }

    @Test
    public void testAPIFilteringByYear(){
        int[] testedYears = {2011,2012,2013,2014,2015,2016,2017};

        boolean hasOnceResults = false;
        for(int year : testedYears){
            List<AirportRecord> filteredResults = RecordFilter.filterByYear(records, year);

            if(!filteredResults.isEmpty())
                hasOnceResults = true;

            for(AirportRecord record: filteredResults){
                assertEquals(record.getTime().getYear(), year);
            }
        }
        assertTrue(hasOnceResults);
    }



    @Test
    public void testAPIFilteringByAirportBetweenYears(){
        // Test 5 scenarios
        int[] testedMinYears = {2011,2012,2013,2014,2015};
        int[] testedMaxYears = {2015,2013,2016,2014,2016};
        String[] testedAirports = {"ATL", "BOS", "BWI", "DCA", "EWR"};


        boolean hasOnceResults = false;
        for(int i = 0; i < 5; i++){
            int minYear = testedMinYears[i];
            int maxYear = testedMaxYears[i];
            String airportCode = testedAirports[i];


            List<AirportRecord> filteredResults = RecordFilter.filterByAirportBetweenYears(records, airportCode, minYear, maxYear);

            if(!filteredResults.isEmpty())
                hasOnceResults = true;

            for(AirportRecord record: filteredResults){
                boolean is_between_years = record.getTime().getYear() >= minYear && record.getTime().getYear() <= maxYear;
                assertTrue(is_between_years);
                assertEquals(record.getAirport().getCode(), airportCode);
            }
        }
        assertTrue(hasOnceResults);
    }


}
