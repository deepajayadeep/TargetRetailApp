package com.target.retail.controller;

import com.mongodb.MongoException;
import com.target.retail.model.PriceDetails;
import com.target.retail.model.Product;
import com.target.retail.services.PriceDetailsService;
import com.target.retail.services.ProductService;
import com.target.retail.services.ProductServiceImpl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductService productServiceImpl;
    public ProductController(ProductService service) {
        this.productServiceImpl = service;
    }
    private final Logger logger = Logger.getLogger(ProductController.class.getName());

   /* @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductDetails(@PathVariable Integer id) throws HTTPException, MongoException, IOException {
        logger.info("Inside  get controller method");
       return productServiceImpl.getProductDetails(id);

    }*/

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable ObjectId id) throws HTTPException, MongoException, IOException {
        logger.info("Inside  get controller method");
       return productServiceImpl.getProductDetails(id);

    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
      return productServiceImpl.findAll();
    }
    
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public Product modifyProductById(@PathVariable ObjectId id,@Valid @RequestBody Product product) throws Exception {
        logger.info("Inside update controller method"+id+"id.toHexString()"+id.toHexString());
        return productServiceImpl.updateProductDetails(id, product);
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@Valid @RequestBody Product product) {
    	product.setId(ObjectId.get());
    	//product.setId(new ObjectId().toId());
    	return productServiceImpl.addProductDetails(product);
    	/*{
  "current_price": {
    "currency_code": "INR",
    "value": 100
  },
  "name": "pRODUCT6"
}
*/
    }
}
