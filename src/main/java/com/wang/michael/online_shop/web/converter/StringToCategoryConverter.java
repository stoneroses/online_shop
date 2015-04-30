package com.wang.michael.online_shop.web.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.wang.michael.online_shop.exception.CategoryNotFound;
import com.wang.michael.online_shop.model.Category;
import com.wang.michael.online_shop.service.CategoryService;

public class StringToCategoryConverter implements Converter<String, Category> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public Category convert(String categoryId) {
        Category category = null;
        if (StringUtils.isNumeric(categoryId)) {
            try {
                category = this.categoryService.findById(Long.valueOf(categoryId));
            } catch (NumberFormatException | CategoryNotFound e) {
            }
        }
        return category;
    }
}
