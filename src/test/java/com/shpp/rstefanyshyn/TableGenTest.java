package com.shpp.rstefanyshyn;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.shpp.rstefanyshyn.Constant.FILE_STORE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

    public class TableGenTest {

        @Mock
        private MongoDatabase mockDatabase;

        @Mock
        private MongoCollection<Document> mockCollection;

        private TableGen tableGen;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            when(mockDatabase.getCollection(anyString())).thenReturn(mockCollection);
            tableGen = new TableGen(mockDatabase);
        }

        @Test
        public void testReader() {
            String file = "adress_stores.csv";
            String nameDocument = "Store";
            String indexName = "address";

            // Mock the input stream with test data
                // Call the method under test
                tableGen.reader(file, nameDocument, indexName);

                // Verify the interactions
                verify(mockDatabase).getCollection(nameDocument);
                verify(mockCollection).drop();
                verify(mockCollection, times(30)).insertOne(any(Document.class));

        }}
