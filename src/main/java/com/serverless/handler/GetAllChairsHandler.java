package com.serverless.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.ApiGatewayResponse;
import com.serverless.Chair;
import com.serverless.ChairsManagement;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetAllChairsHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    @SneakyThrows
    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> stringObjectMap, Context context) {
        List<Chair> chairs = ChairsManagement.getInstant().getChairs();
        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(chairs)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
    }
}
