//package com.serverless.handler;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.serverless.ApiGatewayResponse;
//import com.serverless.Flight;
//import com.serverless.FlightsManagement;
//import lombok.SneakyThrows;
//
//import java.util.Collections;
//import java.util.Map;
//
//public class GetFlightById implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
//    @SneakyThrows
//    @Override
//    public ApiGatewayResponse handleRequest(Map<String, Object> stringObjectMap, Context context) {
//        Map<String,String> pathParameters =  (Map<String,String>)stringObjectMap.get("pathParameters");
//        String flightId = pathParameters.get("id");
//        Flight flight = FlightsManagement.getInstant().getFlightById(flightId);
//        return ApiGatewayResponse.builder()
//                .setStatusCode(200)
//                .setObjectBody(flight)
//                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
//                .build();
//    }
//}