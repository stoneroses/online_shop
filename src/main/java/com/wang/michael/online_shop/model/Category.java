package com.wang.michael.online_shop.model;

import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
@Data
@ToString(exclude = { "products", "parent", "children" })
@EqualsAndHashCode(exclude = { "products", "parent", "children" })
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 256)
    @Size(min = 2, max = 256)
    private String name;

    @Column(length = 1024)
    @Size(min = 2, max = 1024)
    private String description;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "category_product", joinColumns = { @JoinColumn(name = "category_id", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "product_id", updatable = false) })
    private Collection<Product> products;

    @JsonIgnore
    @ManyToOne
    private Category parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Collection<Category> children;

    @Column(name = "sort_order")
    private int sortOrder;

    @JsonIgnore
    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @JsonIgnore
    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;

    public int getProductNumber() {
        int result = 0;
        for (Category category : children) {
            result += category.getProductNumber();
        }
        if (!CollectionUtils.isEmpty(products)) {
            result += this.products.size();
        }
        return result;
    }

    @JsonIgnore
    public Collection<Category> getParents() {
        Collection<Category> result = null;
        if (this.parent != null) {
            result = this.parent.getParents();
        } else {
            result = new ArrayList<Category>();
        }
        result.add(this);
        return result;
    }
}
