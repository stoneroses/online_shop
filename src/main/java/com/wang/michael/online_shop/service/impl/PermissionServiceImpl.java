package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Transactional
    public Permission findById(Long id) throws PermissionNotFound {
        Permission result = permissionRepository.findOne(id);
        if (result == null) {
            throw new PermissionNotFound();
        }
        return result;
    }

    @Override
    public Permission save(Permission permission) {
        if (permission.getId() != null) {
            Permission oldPermission = permissionRepository.findOne(permission.getId());
            permission.setCreatedDateTime(oldPermission.getCreatedDateTime());
            permission.setUpdatedDateTime(new DateTime());
        } else {
            permission.setCreatedDateTime(new DateTime());
        }
        return permissionRepository.save(permission);
    }

    @Override
    public Page<Permission> getPermissions(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return permissionRepository.findAll(pageable);
    }

}
