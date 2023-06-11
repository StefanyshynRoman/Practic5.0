package com.shpp.rstefanyshyn;


public interface Constant {
    GetProperty PROPERTY = new GetProperty("config.properties");
    GetProperty PROPERTY_CONNECTION = new GetProperty("connection.properties");

    String NUMBER_PRODUCTS = PROPERTY.getValueFromProperty("maximum");
    String DDL_SQL="ddl.sql";
    String FILE_STORE=PROPERTY.getValueFromProperty("file_store");;
    String FILE_TYPE= PROPERTY.getValueFromProperty("file_type");

    String URL_MONGO =PROPERTY_CONNECTION.getValueFromProperty("url");
    String DATABASE_NAME="inventory_storage3";
    String USER=PROPERTY_CONNECTION.getValueFromProperty("username.db");
    String PASSWORD=PROPERTY_CONNECTION.getValueFromProperty("password.db");

    String BATCH_SIZE =PROPERTY.getValueFromProperty("bath_size") ;

    double THOUSAND =1000;

    String ADDRESS_VALUES = "INSERT INTO store ( address) VALUES (?)";
    String TYPE_VALUES = "INSERT INTO type ( type) VALUES (?)";
}
