package com.wang.michael.online_shop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.wang.michael.online_shop.exception.ProductNotFound;
import com.wang.michael.online_shop.model.Product;
import com.wang.michael.online_shop.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @Value("${file.uri.root}")
    private String fileURIRoot;

    @ModelAttribute("fileURIRoot")
    public String defaultFileURIRoot() {
        return fileURIRoot;
    }

    @ModelAttribute("pageTitle")
    public String defaultPageTitle() {
        return "Products";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequiresPermissions("product_create")
    public ModelAndView newProductPage() throws Exception {
        ModelAndView mav = new ModelAndView("product-new", "product", new Product());
        mav.addObject("pageTitle", "Create Product");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("product_save")
    public ModelAndView saveProduct(@Valid Product product, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("product-edit");
        }
        ModelAndView mav = new ModelAndView("redirect:/products/list");
        productService.save(product);
        String message = "Product was successfully saved.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    @RequiresPermissions("product_list")
    public ModelAndView productListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("product-index");
        Page<Product> productPage = productService.getProducts(page - 1, size);
        mav.addObject("productPage", productPage);
        mav.addObject("totalPages", productPage.getTotalPages());
        mav.addObject("previousPage", page - 1 > 1 ? page - 1 : 1);
        mav.addObject("currentPage", page);
        mav.addObject("nextPage", page + 1 < productPage.getTotalPages() ? page + 1 : productPage.getTotalPages());
        mav.addObject("pageSize", 10);
        mav.addObject("pageTitle", "Product List");
        return mav;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @RequiresPermissions("product_edit")
    public ModelAndView editProductPage(@PathVariable Integer id) throws ProductNotFound {
        ModelAndView mav = new ModelAndView("product-edit");
        Product product = null;
        product = productService.findById(Long.valueOf(id));
        mav.addObject("product", product);
        mav.addObject("pageTitle", "Edit Product " + product.getName());
        return mav;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @RequiresPermissions("product_delete")
    public ModelAndView deleteProduct(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws ProductNotFound {
        ModelAndView mav = new ModelAndView("redirect:/products/list");
        productService.delete(Long.valueOf(id));
        String message = "Product was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewProductPage(@PathVariable Integer id) throws ProductNotFound {
        ModelAndView mav = new ModelAndView("product-view");
        Product product = null;
        product = productService.findById(Long.valueOf(id));
        mav.addObject("product", product);
        mav.addObject("pageTitle", "View Product " + product.getName());
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @RequiresPermissions("product_list")
    public @ResponseBody List<Product> searchByName(@RequestParam("term") String term) {
        return productService.findByName(term);
    }

}
