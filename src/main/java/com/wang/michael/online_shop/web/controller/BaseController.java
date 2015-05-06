package com.wang.michael.online_shop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.wang.michael.online_shop.model.Category;
import com.wang.michael.online_shop.model.Link;
import com.wang.michael.online_shop.service.CategoryService;
import com.wang.michael.online_shop.service.LinkService;

public class BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LinkService linkService;

    @ModelAttribute("categoryList")
    public List<Category> getCategoryList() {
        return this.categoryService.findAllTopCategory();
    }

    @ModelAttribute("linkList")
    public List<Link> getLinkList() {
        return this.linkService.findAll();
    }

}
