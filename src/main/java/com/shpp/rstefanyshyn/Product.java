package com.shpp.rstefanyshyn;


import jakarta.validation.constraints.*;

public class Product {
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Pattern(regexp = "^(?!.*a).*$")
    String productName;
    int quantity;
    String type;
    String address;
    private boolean valid = true;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

   //public Product(String name, String type, int quantity, String address) {
        public Product(String name, String type,  String address) {
        this.productName = name;
        this.type = type;
       // this.quantity=quantity;
        this.address=address;

    }


    @Override
    public String toString() {
        return "Product{" +
                "name: " + '"' + productName + '"' +
                ", type: " + '"'+type + '"' +
                ", address: " +'"'+ address + '"' +
                '}';

    }
}