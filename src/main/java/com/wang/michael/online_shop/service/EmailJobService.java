package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.EmailJobNotFound;
import com.wang.michael.online_shop.model.EmailJob;

public interface EmailJobService {

    public EmailJob create(EmailJob emailJob);

    public EmailJob delete(Long id) throws EmailJobNotFound;

    public List<EmailJob> findAll();

    public EmailJob save(EmailJob emailJob);

    public EmailJob findById(Long id) throws EmailJobNotFound;

    public List<EmailJob> search(String term) throws EmailJobNotFound;

    public Page<EmailJob> getEmailJobs(int page, int size);

}
