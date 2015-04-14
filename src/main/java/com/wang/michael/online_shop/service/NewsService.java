package com.wang.michael.online_shop.service;

import java.util.List;

import com.wang.michael.online_shop.exception.NewsNotFound;
import com.wang.michael.online_shop.model.News;

public interface NewsService {

  public News create(News news);

  public News delete(int id) throws NewsNotFound;

  public List<News> findAll();

  public News update(News news) throws NewsNotFound;

  public News findById(int id) throws NewsNotFound;
}
