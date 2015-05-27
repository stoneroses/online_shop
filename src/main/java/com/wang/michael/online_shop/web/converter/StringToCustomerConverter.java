package com.wang.michael.online_shop.web.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.wang.michael.online_shop.exception.CustomerNotFound;
import com.wang.michael.online_shop.model.Customer;
import com.wang.michael.online_shop.service.CustomerService;

public class StringToCustomerConverter implements Converter<String, Customer> {

    @Autowired
    private CustomerService customerService;

    @Override
    public Customer convert(String customerId) {
        Customer customer = null;
        if (StringUtils.isNumeric(customerId)) {
            try {
                customer = this.customerService.findById(Long.valueOf(customerId));
            } catch (NumberFormatException | CustomerNotFound e) {
            }
        }
        return customer;
    }
}
