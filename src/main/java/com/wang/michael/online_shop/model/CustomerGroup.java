package com.wang.michael.online_shop.model;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "customer_groups")
@Data
@ToString(exclude = { "customers" })
public class CustomerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 256)
    @Size(min = 2, max = 256)
    private String name;

    @Column(length = 1024)
    @Size(min = 2, max = 1024)
    private String description;

    @ManyToMany(mappedBy = "customerGroups")
    @Basic(fetch = FetchType.LAZY)
    private Collection<Customer> customers;

    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;
}
