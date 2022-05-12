package com.example.blueberry.repository;


import com.example.blueberry.models.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceRepository extends MongoRepository<Invoice,Integer> {
}
