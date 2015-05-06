package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.ProductNotFound;
import com.wang.michael.online_shop.model.Product;
import com.wang.michael.online_shop.repository.ProductRepository;
import com.wang.michael.online_shop.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(rollbackFor = ProductNotFound.class)
    public Product delete(Long id) throws ProductNotFound {
        Product deletedProduct = productRepository.findOne(id);
        if (deletedProduct == null) {
            throw new ProductNotFound();
        }
        productRepository.delete(deletedProduct);
        return deletedProduct;
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product findById(Long id) throws ProductNotFound {
        Product result = productRepository.findOne(id);
        if (result == null) {
            throw new ProductNotFound();
        }
        return result;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        if (product.getId() != null) {
            Product oldProduct = productRepository.findOne(product.getId());
            product.setCreatedDateTime(oldProduct.getCreatedDateTime());
            product.setUpdatedDateTime(new DateTime());
        } else {
            product.setCreatedDateTime(new DateTime());
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByName(String productName) {
        return productRepository.findByName(productName);
    }
}
