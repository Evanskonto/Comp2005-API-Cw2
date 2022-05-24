package unit_tests;

import org.example.statistics.DelayMinutes;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DelayMinutesTests {
    @Test
    //Test if a Carriers object is well created from a mock JSONObject
    public void testCreationFromJson(){
        int carrier = 1;
        int lateAircraft = 1;
        int nationalAviationSystem = 1;
        int security = 1;
        int weather = 1;
        int total = 5;

        JSONObject mockJson = new JSONObject();
        mockJson.put("Carrier", carrier);
        mockJson.put("Late Aircraft", lateAircraft);
        mockJson.put("National Aviation System", nationalAviationSystem);
        mockJson.put("Security", security);
        mockJson.put("Weather", weather);
        mockJson.put("Total", total);

        DelayMinutes delayMinutes = DelayMinutes.fromJson(mockJson);

        assertEquals(delayMinutes.getCarrier(), carrier);
        assertEquals(delayMinutes.getLateAircraft(), lateAircraft);
        assertEquals(delayMinutes.getNationalAviationSystem(), nationalAviationSystem);
        assertEquals(delayMinutes.getSecurity(), security);
        assertEquals(delayMinutes.getWeather(), weather);
        assertEquals(delayMinutes.getTotal(), total);

        //Testing if the total is equal to the sum of the delay minutes should be done in the API testing
    }
}
