package com.shpp.rstefanyshyn;

import org.apache.activemq.util.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
/*
 Generation products: 335912
11.06.2023 18:29:35 [main] INFO  c.s.r.ProductGenerator Generation products is over: seconds - 356.995
11.06.2023 18:35:43 [main] INFO  c.s.r.ProductGenerator Generation products: 336056
11.06.2023 18:35:43 [main] INFO  c.s.r.ProductGenerator Generation products is over: seconds - 299.885
11.06.2023 18:35:43 [main] WARN  c.s.r.ProductGenerator RPS - 1120.6162362238858,

 */

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
        logger.info("Time (second) of create table store and type {} ", (stopWatch.taken()) / THOUSAND_TO_TIME);
        String productType = System.getProperty("type", "test").toLowerCase();
        productGenerator =
                new ProductGenerator(bdConnection.getDatabase());
        productGenerator.createProductStream();
        stopWatch.restart();
        FindProduct findProduct = new FindProduct(bdConnection.getDatabase());
        findProduct.find(productType);
        stopWatch.stop();
        logger.warn("Time (second) of find {} ", (stopWatch.taken()) / THOUSAND_TO_TIME);
        bdConnection.disconnect();
    }
    private static void disconnect() throws SQLException {
        bdConnection.disconnect();
    }



}
