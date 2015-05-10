package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.PigeonSaleNotFound;
import com.wang.michael.online_shop.model.PigeonSale;
import com.wang.michael.online_shop.repository.PigeonSaleRepository;
import com.wang.michael.online_shop.service.PigeonSaleService;

@Service("pigeonSaleService")
public class PigeonSaleServiceImpl implements PigeonSaleService {

    @Autowired
    private PigeonSaleRepository pigeonSaleRepository;

    @Override
    @Transactional(rollbackFor = PigeonSaleNotFound.class)
    public PigeonSale delete(Long id) throws PigeonSaleNotFound {
        PigeonSale deletedPigeonSale = pigeonSaleRepository.findOne(id);
        if (deletedPigeonSale == null) {
            throw new PigeonSaleNotFound();
        }
        pigeonSaleRepository.delete(deletedPigeonSale);
        return deletedPigeonSale;
    }

    @Override
    @Transactional
    public List<PigeonSale> findAll() {
        return pigeonSaleRepository.findAll();
    }

    @Override
    @Transactional
    public PigeonSale findById(Long id) throws PigeonSaleNotFound {
        PigeonSale result = pigeonSaleRepository.findOne(id);
        if (result == null) {
            throw new PigeonSaleNotFound();
        }
        return result;
    }

    @Override
    @Transactional
    public PigeonSale save(PigeonSale pigeonSale) {
        if (pigeonSale.getId() != null) {
            PigeonSale oldPigeonSale = pigeonSaleRepository.findOne(pigeonSale.getId());
            pigeonSale.setCreatedDateTime(oldPigeonSale.getCreatedDateTime());
            pigeonSale.setUpdatedDateTime(new DateTime());
        } else {
            pigeonSale.setCreatedDateTime(new DateTime());
        }
        return pigeonSaleRepository.save(pigeonSale);
    }

    @Override
    public Page<PigeonSale> getPigeonSales(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return pigeonSaleRepository.findAll(pageable);
    }
}
