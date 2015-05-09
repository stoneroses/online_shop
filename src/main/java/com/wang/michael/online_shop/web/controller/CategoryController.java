package com.wang.michael.online_shop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wang.michael.online_shop.exception.CategoryNotFound;
import com.wang.michael.online_shop.model.Category;
import com.wang.michael.online_shop.service.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "Categories";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("category_create")
    public ModelAndView newCategoryPage() throws Exception {
        ModelAndView mav = new ModelAndView("category-new", "category", new Category());
        mav.addObject("pageTitle", "Create Category");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("category_save")
    public ModelAndView saveCategory(@Valid Category category, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("category-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/categories/list");
        categoryService.save(category);
        String message = "Category was successfully saved.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("category_list")
    public ModelAndView categoryListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("category-index");
        Page<Category> categoryPage = categoryService.getTopCategory(page - 1, size);
        mav.addObject("categoryPage", categoryPage);
        mav.addObject("totalPages", categoryPage.getTotalPages());
        mav.addObject("previousPage", page - 1 > 1 ? page - 1 : 1);
        mav.addObject("currentPage", page);
        mav.addObject("nextPage", page + 1 < categoryPage.getTotalPages() ? page + 1 : categoryPage.getTotalPages());
        mav.addObject("pageSize", 10);
        mav.addObject("pageTitle", "Category List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("category_edit")
    public ModelAndView editCategoryPage(@PathVariable Integer id) throws CategoryNotFound {
        ModelAndView mav = new ModelAndView("category-edit");
        Category category = null;
        category = categoryService.findById(Long.valueOf(id));
        mav.addObject("category", category);
        mav.addObject("pageTitle", "Edit Category " + category.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("category_delete")
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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @RequiresPermissions("category_list")
    public @ResponseBody List<Category> searchByName(@RequestParam("term") String term) {
        return categoryService.findByName(term);
    }
}
