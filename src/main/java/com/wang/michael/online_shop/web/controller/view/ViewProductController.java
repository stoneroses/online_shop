package com.wang.michael.online_shop.web.controller.view;

import org.apache.commons.lang3.BooleanUtils;
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

import com.wang.michael.online_shop.exception.ProductNotFound;
import com.wang.michael.online_shop.exception.SettingNotFound;
import com.wang.michael.online_shop.model.Product;
import com.wang.michael.online_shop.service.ProductService;
import com.wang.michael.online_shop.service.SettingService;
import com.wang.michael.online_shop.web.controller.BaseController;

@RestController
@RequestMapping(value = "/products")
public class ViewProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SettingService settingService;

    @ModelAttribute("globalDisplayPrice")
    public boolean globalDisplayPrice() {
        try {
            return BooleanUtils.toBoolean(settingService.findByKey("display_price").getValue());
        } catch (SettingNotFound e) {
            return false;
        }
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
        mav.addObject("pageTitle", "product.page.title.list");
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewProductPage(@PathVariable Integer id) throws ProductNotFound {
        ModelAndView mav = new ModelAndView("view-product");
        Product product = null;
        product = productService.findById(Long.valueOf(id));
        mav.addObject("product", product);
        mav.addObject("pageTitle", "page.empty.page.title");
        mav.addObject("pageTitleArg", product.getName());
        return mav;
    }

}
