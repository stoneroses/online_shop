package com.wang.michael.online_shop.web.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.wang.michael.online_shop.exception.PermissionNotFound;
import com.wang.michael.online_shop.model.Permission;
import com.wang.michael.online_shop.service.PermissionService;

public class StringToPermissionConverter implements Converter<String, Permission> {

    @Autowired
    private PermissionService permissionService;

    @Override
    public Permission convert(String permissionId) {
        Permission permission = null;
        if (StringUtils.isNumeric(permissionId)) {
            try {
                permission = this.permissionService.findById(Long.valueOf(permissionId));
            } catch (NumberFormatException | PermissionNotFound e) {
            }
        }
        return permission;
    }
}
