package com.wang.michael.online_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wang.michael.online_shop.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
