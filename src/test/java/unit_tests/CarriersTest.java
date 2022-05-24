package unit_tests;

import org.example.statistics.Carriers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarriersTest {

    @Test
    //Test if a Carriers object is well created from a well-formatted mock JSONObject
    public void testCreationFromJson(){
        String carrierNamesString = "Carrier1,Carrier2,Carrier3";
        String[] individualNames = carrierNamesString.split(",");
        int totalCarrierValue = 3;

        JSONObject mockJson = new JSONObject();
        mockJson.put("Names", carrierNamesString);
        mockJson.put("Total", totalCarrierValue);
        Carriers carriers = Carriers.fromJson(mockJson);

        List<String> carrierNamesList = carriers.getNames();
        assertEquals(carriers.getTotalCarriers(),totalCarrierValue);
        assertEquals(carrierNamesList.size(), totalCarrierValue);

        int i = 0;
        for(String name : carrierNamesList){
            assertNotNull(name);
            assertEquals(name, individualNames[i]);
            i++;
        }

    }
}
