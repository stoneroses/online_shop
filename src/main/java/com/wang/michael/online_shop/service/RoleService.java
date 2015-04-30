package com.wang.michael.online_shop.service;

import java.util.List;

import com.wang.michael.online_shop.exception.RoleNotFound;
import com.wang.michael.online_shop.model.Role;

public interface RoleService {

    public Role delete(Long id) throws RoleNotFound;

    public List<Role> findAll();

    public Role findById(Long id) throws RoleNotFound;

    public Role save(Role role);

}
