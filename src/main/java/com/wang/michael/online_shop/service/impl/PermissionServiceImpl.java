package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.PermissionNotFound;
import com.wang.michael.online_shop.model.Permission;
import com.wang.michael.online_shop.repository.PermissionRepository;
import com.wang.michael.online_shop.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional
    public Permission create(Permission permission) {
        Permission createdPermission = permission;
        createdPermission.setCreatedDateTime(new DateTime());
        return permissionRepository.save(createdPermission);
    }

    @Override
    @Transactional(rollbackFor = PermissionNotFound.class)
    public Permission delete(Long id) throws PermissionNotFound {
        Permission deletedPermission = permissionRepository.findOne(id);
        if (deletedPermission == null) {
            throw new PermissionNotFound();
        }
        permissionRepository.delete(deletedPermission);
        return deletedPermission;
    }

    @Override
    @Transactional
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = PermissionNotFound.class)
    public Permission update(Permission permission) throws PermissionNotFound {
        Permission updatedPermission = permissionRepository.findOne(permission.getId());
        if (updatedPermission == null) {
            throw new PermissionNotFound();
        }
        updatedPermission.setName(permission.getName());
        updatedPermission.setDescription(permission.getDescription());
        updatedPermission.setUpdatedDateTime(new DateTime());
        return updatedPermission;
    }

    @Override
    @Transactional
    public Permission findById(Long id) throws PermissionNotFound {
        Permission result = permissionRepository.findOne(id);
        if (result == null) {
            throw new PermissionNotFound();
        }
        return result;
    }

}
