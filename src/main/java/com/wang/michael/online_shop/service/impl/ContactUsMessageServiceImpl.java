package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.ContactUsMessageNotFound;
import com.wang.michael.online_shop.model.ContactUsMessage;
import com.wang.michael.online_shop.repository.ContactUsMessageRepository;
import com.wang.michael.online_shop.service.ContactUsMessageService;

@Service("contactUsMessageService")
public class ContactUsMessageServiceImpl implements ContactUsMessageService {

    @Autowired
    private ContactUsMessageRepository contactUsMessageRepository;

    @Override
    @Transactional(rollbackFor = ContactUsMessageNotFound.class)
    public ContactUsMessage delete(Long id) throws ContactUsMessageNotFound {
        ContactUsMessage deletedContactUsMessage = contactUsMessageRepository.findOne(id);
        if (deletedContactUsMessage == null) {
            throw new ContactUsMessageNotFound();
        }
        contactUsMessageRepository.delete(deletedContactUsMessage);
        return deletedContactUsMessage;
    }

    @Override
    @Transactional
    public List<ContactUsMessage> findAll() {
        return contactUsMessageRepository.findAll();
    }

    @Override
    @Transactional
    public ContactUsMessage findById(Long id) throws ContactUsMessageNotFound {
        ContactUsMessage result = contactUsMessageRepository.findOne(id);
        if (result == null) {
            throw new ContactUsMessageNotFound();
        }
        return result;
    }

    @Override
    @Transactional
    public ContactUsMessage save(ContactUsMessage contactUsMessage) {
        if (contactUsMessage.getId() != null) {
            ContactUsMessage oldContactUsMessage = contactUsMessageRepository.findOne(contactUsMessage.getId());
            contactUsMessage.setCreatedDateTime(oldContactUsMessage.getCreatedDateTime());
            contactUsMessage.setUpdatedDateTime(new DateTime());
        } else {
            contactUsMessage.setCreatedDateTime(new DateTime());
        }
        return contactUsMessageRepository.save(contactUsMessage);
    }

    @Override
    public Page<ContactUsMessage> getContactUsMessagePages(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return contactUsMessageRepository.findAll(pageable);
    }

}
