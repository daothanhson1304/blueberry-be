package com.example.blueberry.repository;


import java.util.Optional;

import com.example.blueberry.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends MongoRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
