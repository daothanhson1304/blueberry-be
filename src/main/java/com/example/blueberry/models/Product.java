package com.example.blueberry.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "products")
public class Product {
    @Transient
    public static  final  String SEQUENCE_NAME="product_sequence";

    @Id
    private Integer id;
    private String productName;
    private String image;
    private String description;
    private Integer price;
    private Integer sale;
    private Integer categoryId;
    private Integer quantity;
    private Integer status;

    public Product(Integer id, String productName, String image, String description, Integer price, Integer sale, Integer categoryId, Integer quantity, Integer status) {
        this.id = id;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.sale = sale;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.status = status;
    }

    public String getTitle() {
        return productName;
    }
    public void setTitle(String title) {
        this.productName = title;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}