package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.TestimonialNotFound;
import com.wang.michael.online_shop.model.Testimonial;
import com.wang.michael.online_shop.repository.TestimonialRepository;
import com.wang.michael.online_shop.service.TestimonialService;

@Service("testimonialService")
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Override
    @Transactional(rollbackFor = TestimonialNotFound.class)
    public Testimonial delete(Long id) throws TestimonialNotFound {
        Testimonial deletedTestimonial = testimonialRepository.findOne(id);
        if (deletedTestimonial == null) {
            throw new TestimonialNotFound();
        }
        testimonialRepository.delete(deletedTestimonial);
        return deletedTestimonial;
    }

    @Override
    @Transactional
    public List<Testimonial> findAll() {
        return testimonialRepository.findAll();
    }

    @Override
    @Transactional
    public Testimonial findById(Long id) throws TestimonialNotFound {
        Testimonial result = testimonialRepository.findOne(id);
        if (result == null) {
            throw new TestimonialNotFound();
        }
        return result;
    }

    @Override
    public Testimonial save(Testimonial testimonial) {
        if (testimonial.getId() != null) {
            Testimonial oldTestimonial = testimonialRepository.findOne(testimonial.getId());
            testimonial.setCreatedDateTime(oldTestimonial.getCreatedDateTime());
            testimonial.setUpdatedDateTime(new DateTime());
        } else {
            testimonial.setCreatedDateTime(new DateTime());
        }
        return testimonialRepository.save(testimonial);
    }

    @Override
    public Page<Testimonial> getTestimonial(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return testimonialRepository.findAll(pageable);
    }

}
