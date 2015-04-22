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
  @Transactional
  public Category create(Category category) {
    Category createdCategory = category;
    createdCategory.setCreatedTime(new DateTime());
    return categoryRepository.save(createdCategory);
  }

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
  @Transactional(rollbackFor = CategoryNotFound.class)
  public Category update(Category category) throws CategoryNotFound {
    Category updatedCategory = categoryRepository.findOne(category.getId());
    if (updatedCategory == null) {
      throw new CategoryNotFound();
    }
    updatedCategory.setName(category.getName());
    updatedCategory.setDescription(category.getDescription());
    updatedCategory.setUpdatedDateTime(new DateTime());
    return updatedCategory;
  }

  @Override
  @Transactional
  public Category findById(Long id) throws CategoryNotFound {
    return categoryRepository.findOne(id);
  }

}
