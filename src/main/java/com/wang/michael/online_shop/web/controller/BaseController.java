package com.wang.michael.online_shop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.wang.michael.online_shop.exception.SettingNotFound;
import com.wang.michael.online_shop.model.Category;
import com.wang.michael.online_shop.model.Link;
import com.wang.michael.online_shop.service.CategoryService;
import com.wang.michael.online_shop.service.LinkService;
import com.wang.michael.online_shop.service.SettingService;

public class BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private SettingService settingService;

    @ModelAttribute("categoryList")
    public List<Category> getCategoryList() {
        return this.categoryService.findAllTopCategory();
    }

    @ModelAttribute("linkList")
    public List<Link> getLinkList() {
        return this.linkService.findAll();
    }

    @ModelAttribute("pageTitlePrefix")
    public String getPageTitlePrefix() {
        try {
            return this.settingService.findByKey("page_title_prefix").getValue();
        } catch (SettingNotFound e) {
            return "Un-configurated page_title_prefix";
        }
    }
}
