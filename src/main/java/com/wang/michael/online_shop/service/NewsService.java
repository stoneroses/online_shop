package com.wang.michael.online_shop.service;

import java.util.List;

import com.wang.michael.online_shop.exception.NewsNotFound;
import com.wang.michael.online_shop.model.News;

public interface NewsService {

    public News delete(Long id) throws NewsNotFound;

    public List<News> findAll();

    public News findById(Long id) throws NewsNotFound;

    public News save(News news);
}
