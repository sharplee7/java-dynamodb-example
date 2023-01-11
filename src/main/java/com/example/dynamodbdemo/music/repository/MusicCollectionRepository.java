package com.example.dynamodbdemo.music.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.dynamodbdemo.music.domain.MusicCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MusicCollectionRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public MusicCollection saveMusicCollection(MusicCollection musicCollection) {
        dynamoDBMapper.save(musicCollection);
        return musicCollection;
    }

    public List<MusicCollection> getMusicCollectionByArtist(String artist) {
        Map<String,String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#artist","artist");
        // Key로 사용되는 songTitle도 입력 받아서 검색 조건으로 사용하고 싶으면 아래 주석 해제
//        expressionAttributesNames.put("#songTitle","songTitle");

        Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":artist", new AttributeValue().withS(artist));
        // Key로 사용되는 songTitle도 입력 받아서 검색 조건으로 사용하고 싶으면 아래 주석 해제
//        expressionAttributeValues.put(":songTitle",new AttributeValue().withS("good"));

        DynamoDBQueryExpression<MusicCollection> queryExpression = new DynamoDBQueryExpression<MusicCollection>()
                // Key로 사용되는 songTitle도 입력 받아서 검색 조건으로 사용하고 싶으면 아래 주석 해제
//                .withKeyConditionExpression("#artist = :artist and #songTitle = :songTitle ")
                // artist와 songTitle을 모두 검색키로
                .withKeyConditionExpression("#artist = :artist")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues);

        return dynamoDBMapper.query(MusicCollection.class,queryExpression);


//        return dynamoDBMapper.load(MusicCollection.class, artist);
    }

    public String deleteMusicCollectionByArtist(String artist) {
        dynamoDBMapper.delete(dynamoDBMapper.load(MusicCollection.class, artist));
        return "MusicCollection Artist : "+ artist +" Deleted!";
    }

    public String updateMusicCollection(MusicCollection MusicCollection) {
        dynamoDBMapper.save(MusicCollection,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("artist",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(MusicCollection.getArtist())
                                )
                        ).withExpectedEntry("songTitle",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(MusicCollection.getSongTitle())
                                ))
        );
        return MusicCollection.getArtist();
    }
}