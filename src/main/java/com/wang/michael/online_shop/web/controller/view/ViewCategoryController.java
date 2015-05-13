package com.wang.michael.online_shop.web.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wang.michael.online_shop.exception.CategoryNotFound;
import com.wang.michael.online_shop.model.Category;
import com.wang.michael.online_shop.service.CategoryService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = { "/categories", "/equipments" })
public class ViewCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "equipments";
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView categoryListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("list-category");
        Page<Category> categoryPage = categoryService.getTopCategory(page - 1, size);
        preparePaginationData(mav, "categoryPage", categoryPage, page, 10);
        mav.addObject("pageTitle", "category.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewCategoryPage(@PathVariable Integer id) throws CategoryNotFound {
        ModelAndView mav = new ModelAndView("view-category");
        Category category = null;
        category = categoryService.findById(Long.valueOf(id));
        mav.addObject("category", category);
        mav.addObject("pageTitle", "category.page.title.view");
        mav.addObject("pageTitleArg", category.getName());
        return mav;
    }
}
