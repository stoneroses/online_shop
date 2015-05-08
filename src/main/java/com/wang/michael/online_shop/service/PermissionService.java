package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.PermissionNotFound;
import com.wang.michael.online_shop.model.Permission;

public interface PermissionService {

    public Permission delete(Long id) throws PermissionNotFound;

    public List<Permission> findAll();

    public Page<Permission> getPermissions(int page, int size);

    public Permission findById(Long id) throws PermissionNotFound;

    public Permission save(Permission permission);

}
