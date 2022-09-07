package com.serverless;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.List;

public class FlightsManagement {

    private static final FlightsManagement instant = new FlightsManagement();
    private final DynamoDBAdapter dynamoDBAdapter = new DynamoDBAdapter("flights_tbl");
    private FlightsManagement(){}

    public static FlightsManagement getInstant(){
        return instant;
    }
    public Flight createFlight(Flight flight){
        dynamoDBAdapter.getMapper().save(flight);
        return flight;
    }

    public List<Flight> getFlights(){
        DynamoDBScanExpression scanExp = new DynamoDBScanExpression();

        return dynamoDBAdapter.getMapper().scan(Flight.class, scanExp);
    }

    public Flight updateFlight(String id, Flight flight){
        Flight currentFlight = dynamoDBAdapter.getMapper().load(Flight.class, id);
        if (currentFlight != null) {
            currentFlight.setType(flight.getType());
            currentFlight.setFrom(flight.getFrom());
            currentFlight.setTo(flight.getTo());
            currentFlight.setTime(flight.getTime());
            dynamoDBAdapter.getMapper().save(currentFlight);
            return currentFlight;
        }
        else {
            throw new RuntimeException("Cannot find flight with id: " + id);
        }
    }

    public Flight deleteFlight(String id){
        Flight currentFlight = dynamoDBAdapter.getMapper().load(Flight.class, id);
        if (currentFlight != null) {
            dynamoDBAdapter.getMapper().delete(currentFlight);
            return currentFlight;
        }
        else {
            throw new RuntimeException("Cannot find flight with id: " + id);
        }
    }

    public Flight getFlightById(String flightId) {
        Flight currentFlight = dynamoDBAdapter.getMapper().load(Flight.class, flightId);
        if (currentFlight != null) {
            return currentFlight;
        }
        else {
            throw new RuntimeException("Cannot find flight with id: " + flightId);
        }
    }
}
