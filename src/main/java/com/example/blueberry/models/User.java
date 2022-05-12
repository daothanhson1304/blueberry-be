package com.example.blueberry.models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document(collection = "users")
public class User {
    @Transient
    public static  final  String SEQUENCE_NAME="user_sequence";
    public User(Integer id, String username, String password, String email, Collection<Role> roles,Integer gender,String mobileNumber, String address) {
        this.id = id;

        this.username = username;
        this.password = password;
        this.roles=roles;
        this.email=email;
        this.gender=gender;
        this.mobileNumber=mobileNumber;
        this.address=address;

    }
    @Id
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer gender;
    private String mobileNumber;
    private String address;
    private Collection<Role> roles= new ArrayList<>();

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
    public Collection<Role> getRoles() {
        return roles;
    }




}