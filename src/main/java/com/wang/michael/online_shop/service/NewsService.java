package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.NewsNotFound;
import com.wang.michael.online_shop.model.News;

public interface NewsService {

    public News delete(Long id) throws NewsNotFound;

    public List<News> findAll();

    public News findById(Long id) throws NewsNotFound;

    public News save(News news);

    public Page<News> getNews(int page, int size);
}
