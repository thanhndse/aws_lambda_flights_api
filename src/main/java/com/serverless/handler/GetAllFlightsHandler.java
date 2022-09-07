package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.ApiGatewayResponse;
import com.serverless.Flight;
import com.serverless.FlightsManagement;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetAllFlightsHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    @SneakyThrows
    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> stringObjectMap, Context context) {
        List<Flight> flights = FlightsManagement.getInstant().getFlights();
        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(flights)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
    }
}
