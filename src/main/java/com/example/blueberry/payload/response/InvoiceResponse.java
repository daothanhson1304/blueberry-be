package com.example.blueberry.payload.response;


public class InvoiceResponse {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private String productImage;
    private Integer quantity;
    private Integer totalPrice;
    private Integer status;
    private String productTitle;
    private String productDes;
    private long createAt;

    public InvoiceResponse(Integer id,Integer userId, Integer productId, String productImage, Integer quantity, Integer totalPrice, Integer status, String productTitle, String productDes,long createAt) {
        this.userId = userId;
        this.productId = productId;
        this.productImage = productImage;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.productTitle = productTitle;
        this.productDes = productDes;
        this.id=id;
        this.createAt=createAt;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }
}
