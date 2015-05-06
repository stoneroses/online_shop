package com.wang.michael.online_shop.service;

import java.util.List;

import com.wang.michael.online_shop.exception.CategoryNotFound;
import com.wang.michael.online_shop.model.Category;

public interface CategoryService {

    public Category delete(Long id) throws CategoryNotFound;

    public List<Category> findAll();

    public Category findById(Long id) throws CategoryNotFound;

    public Category save(Category category);

    public List<Category> findByName(String categoryName);

    public List<Category> findAllTopCategory();
}
