package com.example.blueberry.payload.request;


import java.util.List;

public class InvoiceRequest {
    private List<Integer> productIds;
    private Integer userId;

    public InvoiceRequest() {

    }

    public InvoiceRequest(List<Integer> productIds, Integer userId) {
        this.productIds = productIds;
        this.userId = userId;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
