package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.ContactUsMessageNotFound;
import com.wang.michael.online_shop.model.ContactUsMessage;

public interface ContactUsMessageService {

    public ContactUsMessage delete(Long id) throws ContactUsMessageNotFound;

    public List<ContactUsMessage> findAll();

    public ContactUsMessage findById(Long id) throws ContactUsMessageNotFound;

    public ContactUsMessage save(ContactUsMessage contactUsMessage);

    public Page<ContactUsMessage> getContactUsMessagePages(int page, int size);

}
