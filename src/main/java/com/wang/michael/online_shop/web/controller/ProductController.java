package com.wang.michael.online_shop.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wang.michael.online_shop.model.ProductDetails;

@Controller
public class ProductController extends BaseController {

    @Autowired
    ProductDetails productDetails;

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<ProductDetails> getProducts() {
        List<ProductDetails> productsList = new ArrayList<ProductDetails>();
        ProductDetails productDetails1 = new ProductDetails();
        productDetails1.setProductName("test product name 1");
        productDetails1.setId(1);
        ProductDetails productDetails2 = new ProductDetails();
        productDetails2.setProductName("test product name 2");
        productDetails2.setId(2);

        productsList.add(productDetails1);
        productsList.add(productDetails2);
        return productsList;
    }

    @RequestMapping(value = "/products/1", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProductDetails getProduct() {
        ProductDetails productDetails1 = new ProductDetails();
        productDetails1.setProductName("test product name 1");
        productDetails1.setId(1);

        return productDetails1;
    }
}