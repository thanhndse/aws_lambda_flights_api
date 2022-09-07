package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.ApiGatewayResponse;
import com.serverless.Flight;
import com.serverless.FlightsManagement;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.Map;

public class UpdateFlightHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    @SneakyThrows
    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> stringObjectMap, Context context) {
        JsonNode body = new ObjectMapper().readTree((String) stringObjectMap.get("body"));
        Flight flight = new Flight();
        flight.setType(body.get("type").asText());
        flight.setFrom(body.get("from").asText());
        flight.setTo(body.get("to").asText());
        flight.setTime(body.get("time").asText());

        Map<String,String> pathParameters =  (Map<String,String>)stringObjectMap.get("pathParameters");
        String flightId = pathParameters.get("id");

        Flight updatedFlight = FlightsManagement.getInstant().updateFlight(flightId, flight);
        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(updatedFlight)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
    }
}
