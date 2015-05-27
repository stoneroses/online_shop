package com.wang.michael.online_shop.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true)
    @Email(message = "{customer.email.not.correct.format}")
    @NotEmpty(message = "{customer.email.not.empty}")
    private String email;

    @Column(name = "hashed_email")
    private String hashedEmail;

    @Column(length = 256)
    private String title;

    @Column(length = 256)
    private String name;

    @Column
    private boolean subscribe;

    @Column(length = 1024)
    private String description;

    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JsonIgnore
    @JoinTable(name = "customer_group", joinColumns = { @JoinColumn(name = "customer_id", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "group_id", updatable = false) })
    private Collection<CustomerGroup> customerGroups;

    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;
}
