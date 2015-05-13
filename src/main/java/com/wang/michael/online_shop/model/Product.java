package com.wang.michael.online_shop.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
@Data
@ToString(exclude = { "images", "categories" })
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 256)
    @Size(min = 2, max = 256)
    private String name;

    @Column(length = 256)
    @Size(min = 2, max = 256)
    private String reference;

    @Column
    private double weight;

    @Column
    private int stock;

    @Column
    private double price;

    @Column
    private double discount;

    @Column(length = 1024)
    @Size(min = 2, max = 1024)
    private String description;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "product_image", joinColumns = { @JoinColumn(name = "product_id", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "image_id", updatable = false) })
    private Collection<Image> images;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "products")
    private Collection<Category> categories;

    @JsonIgnore
    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @JsonIgnore
    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;

    public double getNowPrice() {
        return this.price * (100 - this.discount) / 100;
    }

    public String getImageLocation() {
        if (!CollectionUtils.isEmpty(this.images)) {
            return ((Image) CollectionUtils.get(images, 0)).getLocation();
        }
        return "";
    }
}
