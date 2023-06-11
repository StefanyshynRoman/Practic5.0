package com.shpp.rstefanyshyn;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class TableGen implements Constant {
    private final Logger logger = LoggerFactory.getLogger(TableGen.class);
    MongoDatabase database;

    public TableGen(MongoDatabase database) {
        this.database = database;

    }

    public void reader(String file, String nameDocument, String indexName) {

        MongoCollection<Document> collection = database.getCollection(nameDocument);
        collection.drop();

        InputStream inputStream = TableGen.class.getClassLoader().getResourceAsStream(file);
        assert inputStream != null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            logger.info("Read from {}", file);
            String line;
            while ((line = reader.readLine()) != null) {
                Document document = new Document(indexName, line);
                collection.insertOne(document);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
