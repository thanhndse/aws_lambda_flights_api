package com.serverless;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.List;

public class ChairsManagement {

    private static final ChairsManagement instant = new ChairsManagement();
    private final DynamoDBAdapter dynamoDBAdapter = new DynamoDBAdapter("chairs_tbl");
    private ChairsManagement(){}

    public static ChairsManagement getInstant(){
        return instant;
    }
    public Chair createChair(Chair chair){
        dynamoDBAdapter.getMapper().save(chair);
        return chair;
    }

    public List<Chair> getChairs(){
        DynamoDBScanExpression scanExp = new DynamoDBScanExpression();

        return dynamoDBAdapter.getMapper().scan(Chair.class, scanExp);
    }

    public Chair updateChair(String id, Chair chair){
        Chair currentChair = dynamoDBAdapter.getMapper().load(Chair.class, id);
        if (currentChair != null) {
            currentChair.setName(chair.getName());
            currentChair.setColor(chair.getColor());
            dynamoDBAdapter.getMapper().save(currentChair);
            return currentChair;
        }
        else {
            throw new RuntimeException("Cannot find chair with id: " + id);
        }
    }

    public Chair deleteChair(String id){
        Chair currentChair = dynamoDBAdapter.getMapper().load(Chair.class, id);
        if (currentChair != null) {
            dynamoDBAdapter.getMapper().delete(currentChair);
            return currentChair;
        }
        else {
            throw new RuntimeException("Cannot find chair with id: " + id);
        }
    }
}
