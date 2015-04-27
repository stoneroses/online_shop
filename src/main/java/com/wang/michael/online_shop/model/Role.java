package com.wang.michael.online_shop.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
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

import lombok.Data;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "roles")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 6177417450707400228L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 256)
    private String name;

    @Column(length = 1024)
    private String description;

    @ManyToMany(mappedBy = "roles")
    @Basic(fetch = FetchType.LAZY)
    private Collection<User> users;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission", joinColumns = { @JoinColumn(name = "role_id", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "permission_id", updatable = false) })
    private Collection<Permission> permissions;

    @Column(name = "createdDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDateTime;

    @Column(name = "updatedDateTime")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDateTime;
}
