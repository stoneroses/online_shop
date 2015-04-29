package com.wang.michael.online_shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.RoleNotFound;
import com.wang.michael.online_shop.model.Permission;
import com.wang.michael.online_shop.model.Role;
import com.wang.michael.online_shop.repository.PermissionRepository;
import com.wang.michael.online_shop.repository.RoleRepository;
import com.wang.michael.online_shop.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional
    public Role create(Role role) {
        Role createdRole = role;
        createdRole.setCreatedDateTime(new DateTime());
        return roleRepository.save(createdRole);
    }

    @Override
    @Transactional(rollbackFor = RoleNotFound.class)
    public Role delete(Long id) throws RoleNotFound {
        Role deletedRole = roleRepository.findOne(id);
        if (deletedRole == null) {
            throw new RoleNotFound();
        }
        roleRepository.delete(deletedRole);
        return deletedRole;
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = RoleNotFound.class)
    public Role update(Role role) throws RoleNotFound {
        Role updatedRole = roleRepository.findOne(role.getId());
        if (updatedRole == null) {
            throw new RoleNotFound();
        }
        updatedRole.setName(role.getName());
        updatedRole.setPermissions(new ArrayList<Permission>());
        if (role.getPermissions() != null) {
            for (Permission permission : role.getPermissions()) {
                if (permission.getId() != null) {
                    updatedRole.getPermissions().add(permissionRepository.findOne(permission.getId()));
                }
            }
        }
        updatedRole.setDescription(role.getDescription());
        updatedRole.setUpdatedDateTime(new DateTime());
        return updatedRole;
    }

    @Override
    @Transactional
    public Role findById(Long id) throws RoleNotFound {
        Role result = roleRepository.findOne(id);
        if (result == null) {
            throw new RoleNotFound();
        }
        return result;
    }

}
