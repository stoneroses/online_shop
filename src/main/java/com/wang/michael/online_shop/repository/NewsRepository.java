package com.wang.michael.online_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wang.michael.online_shop.model.News;

public interface NewsRepository extends JpaRepository<News, Integer>{
  
}
