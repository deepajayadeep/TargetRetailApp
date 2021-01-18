package com.target.retail.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.retail.model.PriceDetails;
import com.target.retail.model.Product;
import com.target.retail.repository.PriceDetailsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceDetailsServiceImpl implements PriceDetailsService{

    private PriceDetailsRepository priceDetailsRepository;

    @Autowired
    public PriceDetailsServiceImpl(PriceDetailsRepository priceDetailsRepository) {
        this.priceDetailsRepository = priceDetailsRepository;
    }

    @Override
    public List<PriceDetails> listAll() {
        List<PriceDetails> products = new ArrayList<>();
        priceDetailsRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public PriceDetails getById(ObjectId id) {
        return priceDetailsRepository.findById(id);
    }

    @Override
    public PriceDetails saveOrUpdate(PriceDetails priceDetails) {
        priceDetailsRepository.save(priceDetails);
        return priceDetails;
    }

   
}