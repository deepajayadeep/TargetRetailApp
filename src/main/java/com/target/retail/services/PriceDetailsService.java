package com.target.retail.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.target.retail.model.PriceDetails;
import com.target.retail.model.Product;

public interface PriceDetailsService {

    List<PriceDetails> listAll();

    PriceDetails getById(ObjectId id);

    PriceDetails saveOrUpdate(PriceDetails price);

   

}
