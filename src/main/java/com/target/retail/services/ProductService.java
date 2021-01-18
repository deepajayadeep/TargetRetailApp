package com.target.retail.services;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;

import com.target.retail.model.PriceDetails;
import com.target.retail.model.Product;

public interface ProductService {
    public Product getProductDetails(ObjectId id) throws IOException;
    public Product updateProductDetails(ObjectId id, Product newProduct) throws Exception;
	
	public Product addProductDetails(@Valid Product product);
	public List<Product> findAll();
}
