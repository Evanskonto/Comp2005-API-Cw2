package functional_tests;

import org.example.AirportClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Requirement3Test {
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
    /*Access the API and return a list of dates where a specific airport, during the period
    between 2003 and 2016, had a maximal and minimal numbers of operating carriers.*/
    public void testRequirement(){

        String invalid_airport = "123";
        String results = client.getDatesMinimalSecurityDelayTime(invalid_airport);
        assertEquals(results, "No matching results found");

        String valid_airport = "LAX";

        results = client.getDatesMinimalSecurityDelayTime(valid_airport);

    }
}

