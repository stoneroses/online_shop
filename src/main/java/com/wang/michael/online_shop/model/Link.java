package com.wang.michael.online_shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "links")
@Data
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 256)
    @Size(min = 2, max = 256)
    private String name;

    @Column(length = 1024)
    @Size(min = 2, max = 1024)
    private String url;

    @Column(name = "new_window")
    private boolean newWindow;

    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;

}
