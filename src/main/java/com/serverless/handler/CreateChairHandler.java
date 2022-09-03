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

public class CreateChairHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    @SneakyThrows
    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> stringObjectMap, Context context) {
        JsonNode body = new ObjectMapper().readTree((String) stringObjectMap.get("body"));
        Chair chair = new Chair();
        chair.setName(body.get("name").asText());
        chair.setColor(body.get("color").asText());
        Chair newChair = ChairsManagement.getInstant().createChair(chair);
        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(newChair)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
    }
}
