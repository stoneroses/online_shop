package com.wang.michael.online_shop.service.impl;

import java.io.File;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.upload.root}")
    private String fileUploadRoot;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Image delete(Long id) throws Exception {
        Image deletedImage = imageRepository.findOne(id);
        if (deletedImage == null) {
            throw new ImageNotFound();
        }
        imageRepository.delete(deletedImage);
        File imageFile = new File(this.fileUploadRoot + deletedImage.getLocation());
        try {
            if (!imageFile.delete()) {
                throw new Exception("Failed to delete image file: " + deletedImage.getLocation());
            }
        } catch (Exception e) {
            throw e;
        }
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
            image.setLocation(oldImage.getLocation());
        } else {
            image.setCreatedDateTime(new DateTime());
        }
        return imageRepository.save(image);
    }

}
