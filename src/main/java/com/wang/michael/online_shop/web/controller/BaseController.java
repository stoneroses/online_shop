package com.wang.michael.online_shop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

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
            return "page.title.prefix.not.configured";
        }
    }

    protected void preparePaginationData(ModelAndView mav, String pageObjectName, Page<?> pageObjec, int page, int pageSize) {
        mav.addObject(pageObjectName, pageObjec);
        mav.addObject("totalPages", pageObjec.getTotalPages());
        mav.addObject("previousPage", page - 1 > 1 ? page - 1 : 1);
        mav.addObject("currentPage", page);
        mav.addObject("nextPage", page + 1 < pageObjec.getTotalPages() ? page + 1 : pageObjec.getTotalPages());
        mav.addObject("pageSize", pageSize);
    }
}
