package com.shpp.rstefanyshyn;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mongodb.client.model.Filters.eq;

import java.util.Arrays;

public class FindProduct {
    private static final Logger logger = LoggerFactory.getLogger(FindProduct.class);

    MongoDatabase database;

    public FindProduct(MongoDatabase database) {
        this.database = database;

    }


    public void find(String productType) {
        Bson filter = eq("type", productType);
        Bson groupBy = Aggregates.group("$address", Accumulators.sum("totalQuantity", "$quantity"));
        Bson sort = Aggregates.sort(Sorts.descending("totalQuantity"));
        Bson project = Aggregates.project(Projections.fields(
                Projections.include("_id"),
                Projections.computed("totalQuantity", "$totalQuantity")));
        int limit = 1;

        MongoCollection<Document> productsCollection = database.getCollection("Products");
        AggregateIterable<Document> result = productsCollection.aggregate(Arrays.asList(
                Aggregates.match(filter),
                groupBy,
                sort,
                project,
                Aggregates.limit(limit)
        ));
        Document firstDocument = result.first();
        if (firstDocument != null) {
            String storeAddress = firstDocument.getString("_id");

            logger.info("The store with the largest number of products of the type '" + productType + "': " + storeAddress);

            Integer totalQuantity = firstDocument.getInteger("totalQuantity");
            if (totalQuantity != null) {
                logger.info("Total Quantity: " + totalQuantity);
            } else {
                logger.info("product not found : " + productType);
            }
        } else {
            logger.info("Store's not found with type : " + productType);
        }
    }

}
