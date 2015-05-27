package com.wang.michael.online_shop.web.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.wang.michael.online_shop.exception.CustomerGroupNotFound;
import com.wang.michael.online_shop.model.CustomerGroup;
import com.wang.michael.online_shop.service.CustomerGroupService;

public class StringToCustomerGroupConverter implements Converter<String, CustomerGroup> {

    @Autowired
    private CustomerGroupService customerGroupService;

    @Override
    public CustomerGroup convert(String groupId) {
        CustomerGroup customerGroup = null;
        if (StringUtils.isNumeric(groupId)) {
            try {
                customerGroup = this.customerGroupService.findById(Long.valueOf(groupId));
            } catch (NumberFormatException | CustomerGroupNotFound e) {
            }
        }
        return customerGroup;
    }
}
