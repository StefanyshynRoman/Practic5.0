package com.shpp.rstefanyshyn;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BDConnectionTest {


    @Test
    public void testConnect() {
        // Create a mock MongoClient
        BDConnection connection = new BDConnection();

        MongoClient mockMongoClient = mock(MongoClient.class);
        // Call the disconnect() method
        boolean result = connection.connect();

        // Verify that the close() method was invoked on the mock MongoClient
        //verify(mockMongoClient, times(1)).close();

        // Check the return value
        assertTrue(result);
    }

    @Test
    public void testDisconnect() {
        // Create an instance of BDConnection
        BDConnection connection = new BDConnection();

        // Call the disconnect() method
        boolean result = connection.disconnect();



        // Check the return value
        assertFalse(result);
    }
    @Test
    public void testGetDatabase() {
        // Create a mock MongoDatabase
        MongoDatabase mockDatabase = mock(MongoDatabase.class);

        // Створюємо екземпляр класу BDConnection
        BDConnection connection = new BDConnection();

        // Встановлюємо підроблений об'єкт MongoDatabase в BDConnection
        connection.setDatabase(mockDatabase);

        // Викликаємо метод getDatabase()
        MongoDatabase result = connection.getDatabase();

        // Перевіряємо повернене значення
        assertEquals(mockDatabase, result);
    }
}