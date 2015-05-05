package com.wang.michael.online_shop.web.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.wang.michael.online_shop.exception.ImageNotFound;
import com.wang.michael.online_shop.model.Image;
import com.wang.michael.online_shop.service.ImageService;

public class StringToImageConverter implements Converter<String, Image> {

    @Autowired
    private ImageService imageService;

    @Override
    public Image convert(String imageId) {
        Image image = null;
        if (StringUtils.isNumeric(imageId)) {
            try {
                image = this.imageService.findById(Long.valueOf(imageId));
            } catch (NumberFormatException | ImageNotFound e) {
            }
        }
        return image;
    }
}
