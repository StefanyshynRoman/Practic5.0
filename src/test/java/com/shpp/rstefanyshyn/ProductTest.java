package com.shpp.rstefanyshyn;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;
public class ProductTest {


        String name = "Test Product";
        String type = "Test type";
        String address = "Test address";

        Product product = new Product(name);

    @Test
    public void testGettersAndSetters() {


        String actualName = product.getProductName();
        String actualType = product.getProductType();
        String actualAddress = product.getProductAddress();
        assertEquals(address, actualAddress);
        assertEquals(name, actualName);
        assertEquals(type, actualType);
    }

    @Test
    public void testToString() {

        String actualToString = product.toString();
        String expectedToString = "Product{name: \"Test Product\", type: \"Test type\", address: \"Test address\"}";
        assertEquals(expectedToString, actualToString);
    }
}
