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
import javax.validation.constraints.Size;

import lombok.Data;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "email_jobs")
@Data
public class EmailJob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 256)
    @Size(min = 2, max = 256)
    private String title;

    @Column(length = 4096)
    @Size(min = 2, max = 4096)
    private String content;

    @Column(name = "activated")
    private boolean activated;

    @Column
    private int success;

    @Column
    private int failure;

    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JsonIgnore
    @JoinTable(name = "email_job_customer_group", joinColumns = { @JoinColumn(name = "email_job_id", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "customer_group_id", updatable = false) })
    private Collection<CustomerGroup> customerGroups;

    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;

    @Column(name = "executedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime executedDateTime;

}
