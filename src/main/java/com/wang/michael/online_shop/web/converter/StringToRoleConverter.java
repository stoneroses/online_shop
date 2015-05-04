package com.wang.michael.online_shop.web.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.wang.michael.online_shop.exception.RoleNotFound;
import com.wang.michael.online_shop.model.Role;
import com.wang.michael.online_shop.service.RoleService;

public class StringToRoleConverter implements Converter<String, Role> {

    @Autowired
    private RoleService roleService;

    @Override
    public Role convert(String roleId) {
        Role role = null;
        if (StringUtils.isNumeric(roleId)) {
            try {
                role = this.roleService.findById(Long.valueOf(roleId));
            } catch (NumberFormatException | RoleNotFound e) {
            }
        }
        return role;
    }
}
