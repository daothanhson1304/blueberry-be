package com.example.blueberry.payload.request;

public class Invoicerq {
    private Integer quantity;
    private Integer invoiceId;

    public Invoicerq() {

    }

    public Invoicerq(Integer quantity, Integer invoiceId) {
        this.quantity = quantity;
        this.invoiceId = invoiceId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
}
