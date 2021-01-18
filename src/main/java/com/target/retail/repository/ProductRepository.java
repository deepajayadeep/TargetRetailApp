package com.target.retail.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.target.retail.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
	Product findById(ObjectId id);
}
