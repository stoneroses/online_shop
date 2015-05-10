package com.wang.michael.online_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wang.michael.online_shop.exception.PigeonSaleNotFound;
import com.wang.michael.online_shop.model.PigeonSale;

public interface PigeonSaleService {

    public PigeonSale delete(Long id) throws PigeonSaleNotFound;

    public List<PigeonSale> findAll();

    public PigeonSale findById(Long id) throws PigeonSaleNotFound;

    public PigeonSale save(PigeonSale pigeonSale);

    public Page<PigeonSale> getPigeonSales(int page, int size);
}
