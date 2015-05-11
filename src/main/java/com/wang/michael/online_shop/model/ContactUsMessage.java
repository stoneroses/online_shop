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
@Table(name = "contact_us_messages")
@Data
public class ContactUsMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 128)
    @Size(min = 2, max = 128)
    private String title;

    @Column(name = "first_name", length = 128)
    @Size(min = 2, max = 128)
    private String firstName;

    @Column(name = "last_name", length = 128)
    @Size(min = 2, max = 128)
    private String lastName;

    @Column(length = 128)
    @Size(min = 2, max = 128)
    private String phone;

    @Column(length = 256)
    @Size(min = 2, max = 256)
    private String email;

    @Column(length = 256)
    @Size(min = 2, max = 256)
    private String subject;

    @Column(length = 2048)
    @Size(min = 2, max = 2048)
    private String content;

    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;

}
