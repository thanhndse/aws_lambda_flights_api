package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.ApiGatewayResponse;
import com.serverless.Chair;
import com.serverless.ChairsManagement;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.Map;

public class DeleteChairHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    @SneakyThrows
    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> stringObjectMap, Context context) {
        Map<String,String> pathParameters =  (Map<String,String>)stringObjectMap.get("pathParameters");
        String chairId = pathParameters.get("id");

        Chair deletedChair = ChairsManagement.getInstant().deleteChair(chairId);
        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(deletedChair)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
    }
}
