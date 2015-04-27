package com.wang.michael.online_shop.service;

import java.util.List;

import com.wang.michael.online_shop.exception.PermissionNotFound;
import com.wang.michael.online_shop.model.Permission;

public interface PermissionService {

    public Permission create(Permission permission);

    public Permission delete(Long id) throws PermissionNotFound;

    public List<Permission> findAll();

    public Permission update(Permission permission) throws PermissionNotFound;

    public Permission findById(Long id) throws PermissionNotFound;

}
