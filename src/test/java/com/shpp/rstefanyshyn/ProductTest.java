package com.shpp.rstefanyshyn;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;
public class ProductTest {


        String name = "Test Product";


        Product product = new Product(name);

    @Test
    public void testGettersAndSetters() {


        String actualName = product.getProductName();


        assertEquals(name, actualName);

    }

    @Test
    public void testToString() {

        String actualToString = product.toString();
        String expectedToString = "Product{name: \"Test Product\"}";
        assertEquals(expectedToString, actualToString);
    }
}
