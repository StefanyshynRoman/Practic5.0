package com.shpp.rstefanyshyn;

import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import org.apache.activemq.util.StopWatch;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;


public class App implements Constant {

    static BDConnection bdConnection = new BDConnection();
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    static StopWatch stopWatch = new StopWatch();
    static ProductGenerator productGenerator;


    public static void main(String[] args) throws Exception {

        BDConnection bdConnection = new BDConnection();
        bdConnection.connect();

        TableGen tableGen = new TableGen(bdConnection.getDatabase());
        stopWatch.restart();
        tableGen.reader(FILE_STORE, "Store", "address");
        tableGen.reader(FILE_TYPE, "Type", "type");
        stopWatch.stop();
        logger.info("Time (second) of create table store and type {} ", (stopWatch.taken()) / THOUSAND);
        String productType = System.getProperty("type", "rrrr").toLowerCase();
        productGenerator =
                new ProductGenerator(bdConnection.getDatabase());
        productGenerator.createProductStream();
        stopWatch.restart();
        FindProduct findProduct = new FindProduct(bdConnection.getDatabase());
        findProduct.find(productType);
        stopWatch.stop();
        logger.warn("Time (second) of find {} ", (stopWatch.taken()) / THOUSAND);
        bdConnection.disconnect();

    }

    private static void disconnect() throws SQLException {
        bdConnection.disconnect();
    }



}
