package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.CustomerGroupNotFound;
import com.wang.michael.online_shop.model.CustomerGroup;
import com.wang.michael.online_shop.repository.CustomerGroupRepository;
import com.wang.michael.online_shop.service.CustomerGroupService;

@Service("customerGroupService")
public class CustomerGroupServiceImpl implements CustomerGroupService {

    @Autowired
    private CustomerGroupRepository customerGroupRepository;

    @Override
    @Transactional(rollbackFor = CustomerGroupNotFound.class)
    public CustomerGroup delete(Long id) throws CustomerGroupNotFound {
        CustomerGroup deletedGroup = customerGroupRepository.findOne(id);
        if (deletedGroup == null) {
            throw new CustomerGroupNotFound();
        }
        customerGroupRepository.delete(deletedGroup);
        return deletedGroup;
    }

    @Override
    @Transactional
    public List<CustomerGroup> findAll() {
        return customerGroupRepository.findAll();
    }

    @Override
    @Transactional
    public CustomerGroup findById(Long id) throws CustomerGroupNotFound {
        CustomerGroup result = customerGroupRepository.findOne(id);
        if (result == null) {
            throw new CustomerGroupNotFound();
        }
        return result;
    }

    @Override
    @Transactional
    public CustomerGroup save(CustomerGroup customerGroup) {
        if (customerGroup.getId() != null) {
            CustomerGroup oldGroup = customerGroupRepository.findOne(customerGroup.getId());
            customerGroup.setCreatedDateTime(oldGroup.getCreatedDateTime());
            customerGroup.setUpdatedDateTime(new DateTime());
        } else {
            customerGroup.setCreatedDateTime(new DateTime());
        }
        return customerGroupRepository.save(customerGroup);
    }

    @Override
    public List<CustomerGroup> findByName(String groupName) {
        return customerGroupRepository.findByName(groupName);
    }

    @Override
    public Page<CustomerGroup> getCustomerGroups(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return customerGroupRepository.findAll(pageable);
    }
}
