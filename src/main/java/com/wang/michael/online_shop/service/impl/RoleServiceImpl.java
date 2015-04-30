package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.RoleNotFound;
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
    @Transactional
    public Role findById(Long id) throws RoleNotFound {
        Role result = roleRepository.findOne(id);
        if (result == null) {
            throw new RoleNotFound();
        }
        return result;
    }

    @Override
    public Role save(Role role) {
        if (role.getId() != null) {
            Role oldRole = roleRepository.findOne(role.getId());
            role.setCreatedDateTime(oldRole.getCreatedDateTime());
            role.setUpdatedDateTime(new DateTime());
        } else {
            role.setCreatedDateTime(new DateTime());
        }
        return roleRepository.save(role);

    }

}
