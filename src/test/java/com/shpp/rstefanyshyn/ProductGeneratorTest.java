package com.shpp.rstefanyshyn;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ProductGeneratorTest {

    @Mock
    private MongoDatabase mockDatabase;

    @Mock
    private MongoCollection<Document> mockTypeCollection;

    @Mock
    private MongoCollection<Document> mockStoreCollection;

    @Mock
    private MongoCollection<Document> mockProductsCollection;

    private ProductGenerator productGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockDatabase.getCollection("Type")).thenReturn(mockTypeCollection);
        when(mockDatabase.getCollection("Store")).thenReturn(mockStoreCollection);
        when(mockDatabase.getCollection("Products")).thenReturn(mockProductsCollection);
        productGenerator = new ProductGenerator(mockDatabase);
    }

//    @Test
//    public void testCreateProductStream() {
//        // Arrange
//        List<Document> typeDocuments = new ArrayList<>();
//        // Додайте документи в typeDocuments
//
//        List<Document> storeDocuments = new ArrayList<>();
//        // Додайте документи в storeDocuments
//
//        when(mockTypeCollection.find()).thenReturn((FindIterable<Document>) typeDocuments);
//        when(mockStoreCollection.find()).thenReturn((FindIterable<Document>) storeDocuments);
//        doNothing().when(mockProductsCollection).drop();
//        when(mockProductsCollection.insertOne(any(Document.class))).thenReturn(null);
//
//        // Act
//        productGenerator.createProductStream();
//
//        // Assert
//        // Перевірте очікуваний результат
//    }
}