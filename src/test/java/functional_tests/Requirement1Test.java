package functional_tests;

import org.example.AirportClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Requirement1Test {

    static AirportClient client;

    //beforeall function to initialize the classes for functional test
    @BeforeAll
    static void initClient(){
        try {
            client = new AirportClient();
        } catch (Exception e){
            fail("Error accessing API");
        }
    }

    @Test
    /*Access the API and return the name of the airport(s) and date(s) in a specific year,
    during the period between 2003 and 2010, who had less delayed and cancelled flights.*/
    public void testRequirement(){

        //Test invalid user input (invalid year)
        int invalid_year_before = 2000;

        String result = client.getAirportWithLessDelaysAndCancels(invalid_year_before);
        assertEquals(result,"Invalid year");

        //Test invalid user input (invalid year)
        int invalid_year_after = 2011;

        result = client.getAirportWithLessDelaysAndCancels(invalid_year_after);
        assertEquals(result,"Invalid year");


        //Test valid year
        int valid_year = 2009;
        result = client.getAirportWithLessDelaysAndCancels(valid_year);
        boolean containsYear = result.contains(""+valid_year);
        assertTrue(containsYear);

    }

}
