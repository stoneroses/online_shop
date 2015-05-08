package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.SettingNotFound;
import com.wang.michael.online_shop.model.Setting;

public interface SettingService {

    public Setting delete(Long id) throws SettingNotFound;

    public List<Setting> findAll();

    public Setting findById(Long id) throws SettingNotFound;

    public Setting findByKey(String key) throws SettingNotFound;

    public Setting save(Setting setting);

    public Page<Setting> getSettings(int page, int size);
}
