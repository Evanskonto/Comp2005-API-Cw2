package functional_tests;

import org.example.AirportClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Requirement2Test {

    static AirportClient client;

    @BeforeAll
    static void initClient(){
        try {
            client = new AirportClient();
        } catch (Exception e){
            fail("Error accessing API");
        }
    }


    @Test
    /*Access the API and return a list of dates, during the period between 2008 and 2016,
    where the delayed time (in minutes) due to security issues in a specific airport was
    minimal (consider the 5 best options).*/
    public void testRequirement(){

        String invalid_airport = "123";
        String results = client.getDatesMinimalSecurityDelayTime(invalid_airport);
        assertEquals(results, "No matching results found");

        String valid_airport = "LAX";
        //Check if with a valid airport, the results are no more than 5
        results = client.getDatesMinimalSecurityDelayTime(valid_airport);
        assertTrue(results.split("\n").length < 6);

        //Test if number of lines is less than 5
    }

}

