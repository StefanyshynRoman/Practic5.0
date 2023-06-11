package com.shpp.rstefanyshyn;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static com.mongodb.client.model.Filters.eq;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class FindProductTest {

    @Mock
    private MongoDatabase mockDatabase;

    @Mock
    private MongoCollection<Document> mockCollection;

    @Mock
    private AggregateIterable<Document> mockResult;

    private FindProduct findProduct;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockDatabase.getCollection(anyString())).thenReturn(mockCollection);
        when(mockCollection.aggregate(anyList())).thenReturn(mockResult); // Додайте цей рядок
        findProduct = new FindProduct(mockDatabase);
    }

    @Test
    public void testFind() {
        // Arrange
        String productType = "rrrr";
        List<Bson> expectedStages = Arrays.asList(
                Aggregates.match(eq("type", productType)),
                Aggregates.group("$address", Accumulators.sum("totalQuantity", "$quantity")),
                Aggregates.sort(Sorts.descending("totalQuantity")),
                Aggregates.project(Projections.fields(
                        Projections.include("_id"),
                        Projections.computed("totalQuantity", "$totalQuantity"))),
                Aggregates.limit(1)
        );

        // Act
        findProduct.find(productType);

        // Assert
        verify(mockCollection).aggregate(argThat(stages -> stages.size() == expectedStages.size() && stages.containsAll(expectedStages)));
    }
}