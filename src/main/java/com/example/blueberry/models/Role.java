package com.example.blueberry.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {


    @Id
    private Long id;
    private String name;
    public Role() {

    }

    public Role(Long id, String rolename) {
        this.id=id;
        this.name=rolename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
