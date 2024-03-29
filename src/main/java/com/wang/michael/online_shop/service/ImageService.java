package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.ImageNotFound;
import com.wang.michael.online_shop.model.Image;

public interface ImageService {

    public Image delete(Long id) throws Exception;

    public List<Image> findAll();

    public Image findById(Long id) throws ImageNotFound;

    public Image save(Image image);

    public List<Image> findByName(String imageName);

    public Page<Image> getImages(int page, int size);
}
