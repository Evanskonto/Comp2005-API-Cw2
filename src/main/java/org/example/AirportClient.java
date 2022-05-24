package org.example;

import org.example.comparators.CarrierSizeComparator;
import org.example.comparators.DelayCancelComparator;
import org.example.comparators.SecurityDelayTimeComparator;
import org.example.filters.RecordFilter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.*;

public class AirportClient {

    public List<AirportRecord> allRecords = new LinkedList<>();

    public AirportClient() throws URISyntaxException, IOException, InterruptedException {
        allRecords = new APIClient().fetchAirportRecords();
    }

    /*Access the API and return the name of the airport(s) and date(s) in a specific year,
    during the period between 2003 and 2010, who had less delayed and cancelled flights.*/
    public String getAirportWithLessDelaysAndCancels(int year){

        int minYear = 2003;
        int maxYear = 2010;

        if(year < minYear || year > maxYear){
            return "Invalid year";
        }

        List<AirportRecord> filteredRecords = RecordFilter.filterByYear(allRecords, year);

        if(filteredRecords.isEmpty()){
            return "No matching results found";
        }

        Collections.sort(filteredRecords, new DelayCancelComparator());

        String airportDates = "";
        for (int i = 0; i < filteredRecords.size()-1; i++) {
            AirportRecord record = filteredRecords.get(i);
            airportDates += record.getAirport().getCode() + " (" + record.getTime().getLabel() + ")\n";

            AirportRecord nextRecord = filteredRecords.get(i+1);
            if(nextRecord.getFlights().getCancelled() + nextRecord.getFlights().getDelayed() !=
            record.getFlights().getCancelled() + record.getFlights().getDelayed() ){
                break;
            }

        }

        return airportDates;

    }

    /*Access the API and return a list of dates, during the period between 2008 and 2016,
    where the delayed time (in minutes) due to security issues in a specific airport was
    minimal (consider the 5 best options).*/
    public String getDatesMinimalSecurityDelayTime(String airportCode){
        int minYear = 2008;
        int maxYear = 2016;

        //Get records for airport between 2008 and 2016
        List<AirportRecord> filteredRecords = RecordFilter.filterByAirportBetweenYears(allRecords, airportCode, 2008, 2016);
        if(filteredRecords.isEmpty()){
            return "No matching results found";
        }

        //Sort records by security delay time
        Collections.sort(filteredRecords, new SecurityDelayTimeComparator());

        // Get 5 first dates of records
        String result = "";
        for(int i = 0; i < 5; i++){
            result += filteredRecords.get(i).getTime().getLabel() + "\n";
        }
        return result;
    }

    /*
    Access the API and return a list of dates where a specific airport, during the period
    between 2003 and 2016, had a maximal and minimal numbers of operating carriers.
     */
    public String getDatesMinimalAndMaximalCarrierNumbers(String airportCode){
        int minYear = 2003;
        int maxYear = 2016;

        //Filter records of the airport between 2003 and 2016
        List<AirportRecord> filteredRecords = RecordFilter.filterByAirportBetweenYears(allRecords, airportCode, 2003, 2016);
        if(filteredRecords.isEmpty()){
            return "No matching results found";
        }

        //Sort by operator size
        Collections.sort(filteredRecords, new CarrierSizeComparator());

        //Get first and last dates form records
        String result = "Minimal carrier numbers: ";
        result += filteredRecords.get(0).getTime().getLabel() + "\n";
        result += "Maximal carrier numbers: " + filteredRecords.get(filteredRecords.size()-1).getTime().getLabel();
        return result;
    }

    /*
    Access the API and return a list of all airports whose numbers of delays, in a specific
    year, due to weather conditions is less than a specific threshold.
     */
    public String getAirportsWithWeatherDelaysLessThan(int year, int threshold){
        //Filter records from year and with a number of weather delays less than threshold

        //return Airport codes
        List<AirportRecord> filteredRecords = RecordFilter.filterByYear(allRecords, year, threshold);
        if(filteredRecords.isEmpty()){
            return "No matching results found";
        }

        //To avoid duplicates
        Set<String> airportSet = new TreeSet<>();
        for(AirportRecord rec: filteredRecords){
            airportSet.add(rec.getAirport().getCode());
        }

        String result = "";
        for(String airport: airportSet){
            result += airport + "\n";
        }

        return result;
    }


    public static void main(String[] args){

        try {
            AirportClient client = new AirportClient();

            System.out.println("--- Airport Statistics ---");
            int option = 0;
            while(true){
                System.out.println("------Menu------------");
                System.out.println("1)  ");
                System.out.println("2)  ");
                System.out.println("3)  ");
                System.out.println("4)  ");
                System.out.println("Any other) Exit ");
                System.out.println("Choose a statistic to search");
                Scanner s = new Scanner(System.in);
                option = Integer.parseInt(s.nextLine());
                if(option == 1){
                    System.out.println("Insert year");
                    int year = Integer.parseInt(s.nextLine());
                    System.out.println(client.getAirportWithLessDelaysAndCancels(year));
                }else if(option == 2){
                    System.out.println("Insert airport code");
                    String airportCode = s.nextLine();
                    System.out.println(client.getDatesMinimalSecurityDelayTime(airportCode));
                }else if(option == 3){
                    System.out.println("Insert airport code");
                    String airportCode = s.nextLine();
                    System.out.println(client.getDatesMinimalAndMaximalCarrierNumbers("TPA"));
                }else if(option == 4){
                    System.out.println("Insert year");
                    int year = Integer.parseInt(s.nextLine());
                    System.out.println("Insert threshold");
                    int threshold = Integer.parseInt(s.nextLine());
                    System.out.println(client.getAirportsWithWeatherDelaysLessThan(year, threshold));
                }else{
                    break;
                }

            }
        } catch (Exception e){
            System.out.println("Error in API access");
            e.printStackTrace();
        }


    }

}
