package com.wang.michael.online_shop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.CategoryNotFound;
import com.wang.michael.online_shop.model.Category;
import com.wang.michael.online_shop.service.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newCategoryPage() throws Exception {
        ModelAndView mav = new ModelAndView("category-new", "category", new Category());
        mav.addObject("pageTitle", "Create Category");
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createNewCategory(@ModelAttribute Category category, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/categories/list");
        categoryService.create(category);
        String message = "Category was successfully created.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView categoryListPage(Model model) {
        ModelAndView mav = new ModelAndView("category-index");
        List<Category> categoryList = categoryService.findAll();
        mav.addObject("categoryList", categoryList);
        mav.addObject("pageTitle", "Category List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editCategoryPage(@PathVariable Integer id) throws CategoryNotFound {
        ModelAndView mav = new ModelAndView("category-edit");
        Category category = null;
        category = categoryService.findById(Long.valueOf(id));
        mav.addObject("category", category);
        mav.addObject("pageTitle", "Edit Category " + category.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editCategory(@ModelAttribute Category category, @PathVariable Integer id, final RedirectAttributes redirectAttributes)
            throws CategoryNotFound {
        ModelAndView mav = new ModelAndView("redirect:/categories/list");
        String message = "Category was successfully updated.";
        categoryService.update(category);
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws CategoryNotFound {
        ModelAndView mav = new ModelAndView("redirect:/categories/list");
        categoryService.delete(Long.valueOf(id));
        String message = "Category was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewCategoryPage(@PathVariable Integer id) throws CategoryNotFound {
        ModelAndView mav = new ModelAndView("category-view");
        Category category = null;
        category = categoryService.findById(Long.valueOf(id));
        mav.addObject("category", category);
        mav.addObject("pageTitle", "View Category " + category.getName());
        return mav;
    }

}
