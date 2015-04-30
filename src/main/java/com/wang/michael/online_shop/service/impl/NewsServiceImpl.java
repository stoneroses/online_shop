package com.wang.michael.online_shop.service.impl;

import java.util.List;

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
    @Transactional(rollbackFor = NewsNotFound.class)
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
    @Transactional
    public News findById(Long id) throws NewsNotFound {
        News result = newsRepository.findOne(id);
        if (result == null) {
            throw new NewsNotFound();
        }
        return result;
    }

    @Override
    public News save(News news) {
        if (news.getId() != null) {
            News oldNews = newsRepository.findOne(news.getId());
            news.setCreatedDateTime(oldNews.getCreatedDateTime());
            news.setUpdatedDateTime(new DateTime());
        } else {
            news.setCreatedDateTime(new DateTime());
        }
        return newsRepository.save(news);
    }

}
