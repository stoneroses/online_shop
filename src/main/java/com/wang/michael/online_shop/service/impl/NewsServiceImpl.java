package com.wang.michael.online_shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.NewsNotFound;
import com.wang.michael.online_shop.model.News;
import com.wang.michael.online_shop.repository.NewsRepository;
import com.wang.michael.online_shop.service.NewsService;

public class NewsServiceImpl implements NewsService {

  @Resource
  private NewsRepository newsRepository;

  @Override
  @Transactional
  public News create(News news) {
    News createdNews = news;
    createdNews.setCreatedTime(new DateTime());
    return newsRepository.save(createdNews);
  }

  @Override
  @Transactional(rollbackFor = NewsNotFound.class)
  public News delete(int id) throws NewsNotFound {
    News deletedNews = newsRepository.findOne(id);
    if (deletedNews == null) {
      throw new NewsNotFound();
    }
    newsRepository.delete(deletedNews);
    return deletedNews;
  }

  @Override
  @Transactional
  public List<News> findAll() {
    return newsRepository.findAll();
  }

  @Override
  @Transactional(rollbackFor = NewsNotFound.class)
  public News update(News news) throws NewsNotFound {
    News updatedNews = newsRepository.findOne((int) news.getId());
    if (updatedNews == null) {
      throw new NewsNotFound();
    }
    updatedNews.setTitle(news.getTitle());
    updatedNews.setContent(news.getContent());
    updatedNews.setUpdatedDateTime(new DateTime());
    return updatedNews;
  }

  @Override
  @Transactional
  public News findById(int id) throws NewsNotFound {
    return newsRepository.findOne(id);
  }

}
