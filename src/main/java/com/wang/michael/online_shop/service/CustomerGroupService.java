package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.CustomerGroupNotFound;
import com.wang.michael.online_shop.model.CustomerGroup;

public interface CustomerGroupService {

    public CustomerGroup delete(Long id) throws CustomerGroupNotFound;

    public List<CustomerGroup> findAll();

    public CustomerGroup findById(Long id) throws CustomerGroupNotFound;

    public CustomerGroup save(CustomerGroup customerGroup);

    public List<CustomerGroup> findByName(String groupName);

    public Page<CustomerGroup> getCustomerGroups(int page, int size);
}
