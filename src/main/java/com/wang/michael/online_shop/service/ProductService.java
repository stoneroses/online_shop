package com.wang.michael.online_shop.service;

import java.util.List;

import com.wang.michael.online_shop.exception.ProductNotFound;
import com.wang.michael.online_shop.model.Product;

public interface ProductService {

    public Product delete(Long id) throws ProductNotFound;

    public List<Product> findAll();

    public Product findById(Long id) throws ProductNotFound;

    public Product save(Product product);

    public List<Product> findByName(String productName);
}
