package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.CategoryNotFound;
import com.wang.michael.online_shop.model.Category;
import com.wang.michael.online_shop.repository.CategoryRepository;
import com.wang.michael.online_shop.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(rollbackFor = CategoryNotFound.class)
    public Category delete(Long id) throws CategoryNotFound {
        Category deletedCategory = categoryRepository.findOne(id);
        if (deletedCategory == null) {
            throw new CategoryNotFound();
        }
        categoryRepository.delete(deletedCategory);
        return deletedCategory;
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category findById(Long id) throws CategoryNotFound {
        Category result = categoryRepository.findOne(id);
        if (result == null) {
            throw new CategoryNotFound();
        }
        return result;
    }

    @Override
    @Transactional
    public Category save(Category category) {
        if (category.getId() != null) {
            Category oldCategory = categoryRepository.findOne(category.getId());
            category.setCreatedDateTime(oldCategory.getCreatedDateTime());
            category.setUpdatedDateTime(new DateTime());
        } else {
            category.setCreatedDateTime(new DateTime());
        }
        return categoryRepository.save(category);
    }

}
