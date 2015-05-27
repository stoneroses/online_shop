package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.CustomerNotFound;
import com.wang.michael.online_shop.model.Customer;

public interface CustomerService {

    public Customer create(Customer customer);

    public Customer delete(Long id) throws CustomerNotFound;

    public List<Customer> findAll();

    public Customer update(Customer customer) throws CustomerNotFound;

    public Customer save(Customer customer);

    public Customer findById(Long id) throws CustomerNotFound;

    public Customer getByEmail(String email) throws CustomerNotFound;

    public Customer getByHashedEmail(String hashedEmail) throws CustomerNotFound;

    public Page<Customer> getCustomers(int page, int size);

}
