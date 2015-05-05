package com.wang.michael.online_shop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.wang.michael.online_shop.model.Category;
import com.wang.michael.online_shop.service.CategoryService;

public class BaseController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categoryList")
    public List<Category> getCategoryList() {
        return this.categoryService.findAll();
    }

}
