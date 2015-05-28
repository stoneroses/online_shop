package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.EmailJobNotFound;
import com.wang.michael.online_shop.model.EmailJob;
import com.wang.michael.online_shop.repository.EmailJobRepository;
import com.wang.michael.online_shop.service.EmailJobService;

@Service("emailJobService")
public class EmailJobServiceImpl implements EmailJobService {

    @Autowired
    private EmailJobRepository emailJobRepository;

    @Override
    @Transactional
    public EmailJob create(EmailJob emailJob) {
        EmailJob createdEmailJob = emailJob;
        createdEmailJob.setCreatedDateTime(new DateTime());
        return emailJobRepository.save(createdEmailJob);
    }

    @Override
    @Transactional(rollbackFor = EmailJobNotFound.class)
    public EmailJob delete(Long id) throws EmailJobNotFound {
        EmailJob deletedEmailJob = emailJobRepository.findOne(id);
        if (deletedEmailJob == null) {
            throw new EmailJobNotFound();
        }
        emailJobRepository.delete(deletedEmailJob);
        return deletedEmailJob;
    }

    @Override
    @Transactional
    public List<EmailJob> findAll() {
        return emailJobRepository.findAll();
    }

    @Override
    @Transactional
    public EmailJob findById(Long id) throws EmailJobNotFound {
        EmailJob result = emailJobRepository.findOne(id);
        if (result == null) {
            throw new EmailJobNotFound();
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = EmailJobNotFound.class)
    public EmailJob save(EmailJob emailJob) {
        if (emailJob.getId() != null) {
            EmailJob oldEmailJob = emailJobRepository.findOne(emailJob.getId());
            emailJob.setCreatedDateTime(oldEmailJob.getCreatedDateTime());
        } else {
            emailJob.setCreatedDateTime(new DateTime());
        }
        return emailJobRepository.save(emailJob);
    }

    @Override
    public Page<EmailJob> getEmailJobs(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return emailJobRepository.findAll(pageable);
    }

    @Override
    public List<EmailJob> search(String term) throws EmailJobNotFound {
        return this.emailJobRepository.findByTitleOrContent(term);
    }
}
