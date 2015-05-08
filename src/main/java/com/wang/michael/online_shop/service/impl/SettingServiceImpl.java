package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.SettingNotFound;
import com.wang.michael.online_shop.model.Setting;
import com.wang.michael.online_shop.repository.SettingRepository;
import com.wang.michael.online_shop.service.SettingService;

@Service("settingService")
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingRepository settingRepository;

    @Override
    @Transactional(rollbackFor = SettingNotFound.class)
    public Setting delete(Long id) throws SettingNotFound {
        Setting deletedSetting = settingRepository.findOne(id);
        if (deletedSetting == null) {
            throw new SettingNotFound();
        }
        settingRepository.delete(deletedSetting);
        return deletedSetting;
    }

    @Override
    @Transactional
    public List<Setting> findAll() {
        return settingRepository.findAll();
    }

    @Override
    @Transactional
    public Setting findById(Long id) throws SettingNotFound {
        Setting result = settingRepository.findOne(id);
        if (result == null) {
            throw new SettingNotFound();
        }
        return result;
    }

    @Override
    public Setting save(Setting setting) {
        if (setting.getId() != null) {
            Setting oldSetting = settingRepository.findOne(setting.getId());
            setting.setCreatedDateTime(oldSetting.getCreatedDateTime());
            setting.setUpdatedDateTime(new DateTime());
        } else {
            setting.setCreatedDateTime(new DateTime());
        }
        return settingRepository.save(setting);

    }

    @Override
    public Setting findByKey(String key) throws SettingNotFound {
        Setting result = settingRepository.findByKey(key);
        if (result == null) {
            throw new SettingNotFound("key: " + key + " is not found.");
        }
        return result;
    }

    @Override
    public Page<Setting> getSettings(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return settingRepository.findAll(pageable);
    }

}
