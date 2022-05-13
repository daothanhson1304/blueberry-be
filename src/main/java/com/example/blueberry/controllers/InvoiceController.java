package com.example.blueberry.controllers;


import com.example.blueberry.models.Invoice;
import com.example.blueberry.models.Product;
import com.example.blueberry.payload.request.InvoiceRequest;
import com.example.blueberry.payload.request.Invoicerq;
import com.example.blueberry.payload.response.InvoiceResponse;
import com.example.blueberry.repository.InvoiceRepository;
import com.example.blueberry.repository.ProductRepository;
import com.example.blueberry.security.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Invoice>> getAllInvoice() {
        return ResponseEntity.ok(invoiceRepository.findAll());
    }
    @GetMapping("/getInvoiceOfUser/{id}")
    public ResponseEntity<List<InvoiceResponse>> getAllInvoice(@PathVariable("id") Integer id) {
        List<InvoiceResponse> response= new ArrayList<>();
        List<Invoice> userInvoice= new ArrayList<>();
        List<Invoice> listInvoice=invoiceRepository.findAll();
        for(Invoice invoice:listInvoice){
            if(Objects.equals(invoice.getUserId(), id)){
                userInvoice.add(invoice);
            }
        }
        for (Invoice invoice:userInvoice){
            Product product= productRepository.findById(invoice.getProductId()).orElseThrow(()->new ResourceAccessException("Product not found"));
            response.add(new InvoiceResponse(invoice.getId(),invoice.getUserId(),invoice.getProductId(),product.getImage(),invoice.getQuantity(),invoice.getTotalPrice(),invoice.getStatus(),product.getTitle(),product.getDescription(),invoice.getTime_created()));
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/add")
    public Invoice addInvoice(@RequestBody  Invoice invoice){
        invoice.setId(sequenceGeneratorService.getSequenceNumber(Invoice.SEQUENCE_NAME));
        return invoiceRepository.save(invoice);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable("id") Integer id){
        invoiceRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
    @PutMapping("/update")
    public ResponseEntity<List<InvoiceResponse>>  updateInvoice(@RequestBody InvoiceRequest invoiceRequest){
        for(Invoicerq invoicerq:invoiceRequest.getInvoicerqList()){
            System.out.println(invoiceRequest);
            Invoice invoice = invoiceRepository.findById(invoicerq.getInvoiceId()).orElseThrow(()->new ResourceAccessException("Product not found"));
            if(invoice.getStatus()==0){
                invoice.setStatus(1);
            }
            invoice.setQuantity(invoicerq.getQuantity());
            invoiceRepository.save(invoice);
        }
        List<InvoiceResponse> response= new ArrayList<>();
        List<Invoice> userInvoice= new ArrayList<>();
        List<Invoice> listInvoice=invoiceRepository.findAll();
        for(Invoice invoice:listInvoice){
            if(Objects.equals(invoice.getUserId(), invoiceRequest.getUserId())){
                userInvoice.add(invoice);
            }
        }
        for (Invoice invoice:userInvoice){
            Product product= productRepository.findById(invoice.getProductId()).orElseThrow(()->new ResourceAccessException("Product not found"));
            response.add(new InvoiceResponse(invoice.getId(),invoice.getUserId(),invoice.getProductId(),product.getImage(),invoice.getQuantity(),invoice.getTotalPrice(),invoice.getStatus(),product.getTitle(),product.getDescription(),System.currentTimeMillis()));
        }
        return ResponseEntity.ok(response);
    }

}
