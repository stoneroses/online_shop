package com.wang.michael.online_shop.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "permissions")
@Data
@ToString(exclude = { "roles" })
public class Permission implements Serializable {

    private static final long serialVersionUID = -8792590494605747957L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 256)
    @NotNull
    @Size(min = 2, max = 256)
    private String name;

    @Column(length = 1024)
    @Size(min = 2, max = 1024)
    private String description;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "permissions")
    private Collection<Role> roles;

    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;

}
