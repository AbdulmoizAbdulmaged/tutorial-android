package com.example.oracledb;

import java.util.Date;

public class Product {

    private  int id;
    private  String category;
    private  String productName;
    private Double productPrice;
    private Date createDate;

    public Product() {
    }

    public Product(int id, String category, String productName, Double productPrice, Date createDate) {
        this.id = id;
        this.category = category;
        this.productName = productName;
        this.productPrice = productPrice;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
