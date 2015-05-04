package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.ImageNotFound;
import com.wang.michael.online_shop.model.Image;
import com.wang.michael.online_shop.repository.ImageRepository;
import com.wang.michael.online_shop.service.ImageService;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional(rollbackFor = ImageNotFound.class)
    public Image delete(Long id) throws ImageNotFound {
        Image deletedImage = imageRepository.findOne(id);
        if (deletedImage == null) {
            throw new ImageNotFound();
        }
        imageRepository.delete(deletedImage);
        return deletedImage;
    }

    @Override
    @Transactional
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    @Transactional
    public Image findById(Long id) throws ImageNotFound {
        Image result = imageRepository.findOne(id);
        if (result == null) {
            throw new ImageNotFound();
        }
        return result;
    }

    @Override
    @Transactional
    public Image save(Image image) {
        if (image.getId() != null) {
            Image oldImage = imageRepository.findOne(image.getId());
            image.setCreatedDateTime(oldImage.getCreatedDateTime());
            image.setUpdatedDateTime(new DateTime());
        } else {
            image.setCreatedDateTime(new DateTime());
        }
        return imageRepository.save(image);
    }

}
