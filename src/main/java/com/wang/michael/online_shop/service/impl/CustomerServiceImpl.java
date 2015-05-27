package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.CustomerNotFound;
import com.wang.michael.online_shop.model.Customer;
import com.wang.michael.online_shop.repository.CustomerRepository;
import com.wang.michael.online_shop.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer create(Customer customer) {
        Customer createdCustomer = customer;
        createdCustomer.setHashedEmail(getHashedEmail(customer.getEmail(), customer.getEmail()));
        createdCustomer.setCreatedDateTime(new DateTime());
        return customerRepository.save(createdCustomer);
    }

    @Override
    @Transactional(rollbackFor = CustomerNotFound.class)
    public Customer delete(Long id) throws CustomerNotFound {
        Customer deletedCustomer = customerRepository.findOne(id);
        if (deletedCustomer == null) {
            throw new CustomerNotFound();
        }
        customerRepository.delete(deletedCustomer);
        return deletedCustomer;
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = CustomerNotFound.class)
    public Customer update(Customer customer) throws CustomerNotFound {
        Customer updatedCustomer = customerRepository.findOne(customer.getId());
        if (updatedCustomer == null) {
            throw new CustomerNotFound();
        }
        updatedCustomer.setName(customer.getName());
        updatedCustomer.setEmail(customer.getEmail());
        updatedCustomer.setDescription(customer.getDescription());
        updatedCustomer.setUpdatedDateTime(new DateTime());
        return updatedCustomer;
    }

    @Override
    @Transactional
    public Customer findById(Long id) throws CustomerNotFound {
        Customer result = customerRepository.findOne(id);
        if (result == null) {
            throw new CustomerNotFound();
        }
        return result;
    }

    @Override
    public Customer getByEmail(String email) throws CustomerNotFound {
        List<Customer> result = customerRepository.findByEmail(email);
        if (result == null) {
            throw new CustomerNotFound("No customer found.");
        } else if (result.size() > 1) {
            throw new CustomerNotFound("More than one customer found. Please contact admin.");
        } else if (result.size() == 0) {
            throw new CustomerNotFound("No customer found.");
        } else {
            return result.get(0);
        }
    }

    private String getHashedEmail(String email, String salt) {
        return new Sha256Hash(email, salt).toString();
    }

    @Override
    @Transactional(rollbackFor = CustomerNotFound.class)
    public Customer save(Customer customer) {
        if (customer.getId() != null) {
            Customer oldCustomer = customerRepository.findOne(customer.getId());
            customer.setCreatedDateTime(oldCustomer.getCreatedDateTime());
            customer.setHashedEmail(oldCustomer.getHashedEmail());
            customer.setUpdatedDateTime(new DateTime());
        } else {
            customer.setCreatedDateTime(new DateTime());
            customer.setHashedEmail(getHashedEmail(customer.getEmail(), customer.getEmail()));
        }
        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> getCustomers(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getByHashedEmail(String hashedEmail) throws CustomerNotFound {
        return customerRepository.getByHashedEmail(hashedEmail);
    }
}
