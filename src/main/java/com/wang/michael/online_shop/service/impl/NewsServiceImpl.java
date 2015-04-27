package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.NewsNotFound;
import com.wang.michael.online_shop.model.News;
import com.wang.michael.online_shop.repository.NewsRepository;
import com.wang.michael.online_shop.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    @Transactional
    @RequiresRoles("user")
    public News create(News news) {
        News createdNews = news;
        createdNews.setCreatedDateTime(new DateTime());
        return newsRepository.save(createdNews);
    }

    @Override
    @Transactional(rollbackFor = NewsNotFound.class)
    @RequiresRoles("admin")
    public News delete(Long id) throws NewsNotFound {
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
    @RequiresRoles("user")
    public News update(News news) throws NewsNotFound {
        News updatedNews = newsRepository.findOne(news.getId());
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
    public News findById(Long id) throws NewsNotFound {
        News result = newsRepository.findOne(id);
        if (result == null) {
            throw new NewsNotFound();
        }
        return result;
    }

}
