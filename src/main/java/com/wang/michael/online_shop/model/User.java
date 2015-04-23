package com.wang.michael.online_shop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "description")
    private String description;

    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;

    @Transient
    private List<ProductDetails> productList;

    public User(Long id, String name, String description, List<ProductDetails> productList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDateTime = new DateTime(2015, 4, id.intValue(), id.intValue(), 00, 00);
        this.updatedDateTime = new DateTime(2015, 4, id.intValue() * 2, id.intValue() * 2, 00, 00);
        this.productList = productList;
    }

    public User() {
        this.productList = new ArrayList<ProductDetails>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedTime(DateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public DateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(DateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public List<ProductDetails> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDetails> productList) {
        this.productList = productList;
    }

    public void setCreatedDateTime(DateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
