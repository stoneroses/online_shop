package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.TestimonialNotFound;
import com.wang.michael.online_shop.model.Testimonial;

public interface TestimonialService {

    public Testimonial delete(Long id) throws TestimonialNotFound;

    public List<Testimonial> findAll();

    public Testimonial findById(Long id) throws TestimonialNotFound;

    public Testimonial save(Testimonial Testimonial);

    public Page<Testimonial> getTestimonial(int page, int size);
}
