package com.example.blueberry.payload.request;


import java.util.List;


public class InvoiceRequest {
    private Integer userId;
    private List<Invoicerq> invoicerqList;

    public InvoiceRequest(Integer userId, List<Invoicerq> invoicerqList) {
        this.userId = userId;
        this.invoicerqList = invoicerqList;
    }
    public InvoiceRequest() {

    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Invoicerq> getInvoicerqList() {
        return invoicerqList;
    }

    public void setInvoicerqList(List<Invoicerq> invoicerqList) {
        this.invoicerqList = invoicerqList;
    }
}
