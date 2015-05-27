package com.wang.michael.online_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wang.michael.online_shop.model.CustomerGroup;

@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Long> {

    @Query("SELECT customerGroup FROM CustomerGroup customerGroup WHERE LOWER(customerGroup.name) LIKE %:groupName%")
    List<CustomerGroup> findByName(@Param("groupName") String groupName);
}
