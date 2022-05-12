package com.example.blueberry.controllers;


import com.example.blueberry.models.Invoice;
import com.example.blueberry.models.Product;
import com.example.blueberry.payload.request.InvoiceRequest;
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
            response.add(new InvoiceResponse(invoice.getId(),invoice.getUserId(),invoice.getProductId(),product.getImage(),invoice.getQuantity(),invoice.getTotalPrice(),invoice.getStatus(),product.getTitle(),product.getDescription()));
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
    public List<Invoice> updateInvoice(@RequestBody InvoiceRequest invoiceRequest){
        List<Invoice> userInvoice= new ArrayList<>();
        List<Invoice> response= new ArrayList<>();
        List<Invoice> listInvoice=invoiceRepository.findAll();
        for(Invoice invoice:listInvoice){

            if(Objects.equals(invoice.getUserId(), invoiceRequest.getUserId())){
                userInvoice.add(invoice);
            }
        }


        for (Invoice invoice:userInvoice){
            boolean checkProductId=invoiceRequest.getProductIds().contains(invoice.getProductId());
            if(checkProductId&&invoice.getStatus()==0){
                invoice.setStatus(1);
                response.add(invoice);
                invoiceRepository.save(invoice);
            }
        }
        return response;
    }
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Integer> deleteById(@PathVariable("id") Integer id){
//        productRepository.deleteById(id);
//        return ResponseEntity.ok(id);
//    }
//    @GetMapping("/get/{id}")
//    public ResponseEntity<Product> getProductbyId(@PathVariable("id") Integer id){
//        Product product=productRepository.findById(id).orElseThrow(()->new ResourceAccessException("Product not found"));;
//        return ResponseEntity.ok(product);
//    }
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<Product> editProduct(@PathVariable("id") Integer id,@RequestBody Product product) {
//        Product currentProduct=productRepository.findById(id).orElseThrow(()->new ResourceAccessException("Product not found"));
//        currentProduct.setCategoryId(product.getCategoryId());
//        currentProduct.setDescription(product.getDescription());
//
//        currentProduct.setPrice(product.getPrice());
//
//        currentProduct.setImage(product.getImage());
//
//        currentProduct.setQuantity(product.getQuantity());
//        currentProduct.setSale(product.getSale());
//
//        currentProduct.setStatus(product.getStatus());
//        currentProduct.setTitle(product.getTitle());
//        productRepository.save(currentProduct);
//        return ResponseEntity.ok(product);
//    }
}
