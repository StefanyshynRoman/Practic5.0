package com.shpp.rstefanyshyn;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BDConnection implements Constant {
    private final Logger logger = LoggerFactory.getLogger(BDConnection.class);
    private MongoClient mongoClient;
    private MongoDatabase database;
    public boolean connect()  {

        mongoClient = MongoClients.create(URL_MONGO);
        database = mongoClient.getDatabase(DATABASE_NAME);
        logger.info("Connected to the MongoDB database.");
        return true;
    }

    public boolean disconnect()  {
        if (mongoClient != null) {
            mongoClient.close();
            logger.info("Disconnected from the MongoDB database.");
        }
        return false;
    }
    public MongoDatabase getDatabase() {
        return database;
    }
    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }
}