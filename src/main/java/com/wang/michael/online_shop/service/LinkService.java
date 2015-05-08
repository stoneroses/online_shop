package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.LinkNotFound;
import com.wang.michael.online_shop.model.Link;

public interface LinkService {

    public Link delete(Long id) throws LinkNotFound;

    public List<Link> findAll();

    public Link findById(Long id) throws LinkNotFound;

    public Link save(Link category);

    public Page<Link> getLinks(int page, int size);
}
