package com.wang.michael.online_shop.web.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wang.michael.online_shop.exception.ProductNotFound;
import com.wang.michael.online_shop.model.Product;
import com.wang.michael.online_shop.service.ProductService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/products")
public class ViewProductController extends BaseController {

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

    @ModelAttribute("currentNavButton")
    public String getCurrentNavButton() {
        return "equipments";
    }

    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView productListPage(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size) {
        ModelAndView mav = new ModelAndView("list-product");
        Page<Product> productPage = productService.getProducts(page - 1, size);
        mav.addObject("productPage", productPage);
        preparePaginationData(mav, "productPage", productPage, page, 10);
        mav.addObject("pageTitle", "Product List");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewProductPage(@PathVariable Integer id) throws ProductNotFound {
        ModelAndView mav = new ModelAndView("view-product");
        Product product = null;
        product = productService.findById(Long.valueOf(id));
        mav.addObject("product", product);
        mav.addObject("pageTitle", "View Product " + product.getName());
        return mav;
    }

}
