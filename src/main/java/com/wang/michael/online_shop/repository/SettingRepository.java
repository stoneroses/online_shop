package com.wang.michael.online_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wang.michael.online_shop.model.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

    @Query("SELECT setting FROM Setting setting WHERE LOWER(setting.key) = LOWER(:key)")
    Setting findByKey(@Param("key") String key);

}
