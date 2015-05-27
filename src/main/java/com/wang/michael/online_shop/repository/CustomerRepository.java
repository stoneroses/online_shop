package com.wang.michael.online_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wang.michael.online_shop.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT customer FROM Customer customer WHERE LOWER(customer.hashedEmail) = LOWER(:hashedEmail)")
    public Customer getByHashedEmail(@Param("hashedEmail") String hashedEmail);

    @Query("SELECT customer FROM Customer customer WHERE LOWER(customer.email) LIKE %:email% ")
    public List<Customer> findByEmail(@Param("email") String email);

}
