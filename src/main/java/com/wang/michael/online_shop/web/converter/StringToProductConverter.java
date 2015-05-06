package com.wang.michael.online_shop.web.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.wang.michael.online_shop.exception.ProductNotFound;
import com.wang.michael.online_shop.model.Product;
import com.wang.michael.online_shop.service.ProductService;

public class StringToProductConverter implements Converter<String, Product> {

    @Autowired
    private ProductService productService;

    @Override
    public Product convert(String productId) {
        Product product = null;
        if (StringUtils.isNumeric(productId)) {
            try {
                product = this.productService.findById(Long.valueOf(productId));
            } catch (NumberFormatException | ProductNotFound e) {
            }
        }
        return product;
    }
}
