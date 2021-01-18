package com.target.retail.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.target.retail.model.PriceDetails;
import com.target.retail.model.Product;
import com.target.retail.repository.PriceDetailsRepository;
import com.target.retail.repository.ProductRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

@Service
public class ProductServiceImpl implements ProductService{
    private final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private PriceDetailsRepository priceDetailsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment env;
    @Override
    public Product getProductDetails(ObjectId id) throws IOException {
        logger.info("in  getProductDetails ");
       
        Product product= productRepository.findById(id);
        logger.info("prodDetails: "+product);
        return product;
    }

    @Override
    public Product updateProductDetails(ObjectId id, Product updatedProduct) throws Exception {
    	logger.info("updateProductDetails: "+id);
        PriceDetails priceDetails=updatedProduct.getPriceInfo();
        logger.info("updateProductDetails: priceDetails "+priceDetails);
        if(priceDetails.getCurrency_code()==null || priceDetails.getValue()<=0.0){
            throw new Exception(" Please give correct product price and currency code details");
        }
        priceDetails.setId(id);
      //  String productName=getProductDesc(id);
      //  updatedProduct.setName(productName);
        logger.info("updateProductDetails: priceDetails "+updatedProduct);
        PriceDetails pd =updateProductPrice(id,updatedProduct);
        updatedProduct.setId(id);
        logger.info("PriceDetails updatedProduct.getId()"+updatedProduct.getId());
        updatedProduct.setPriceInfo(pd);
        return updatedProduct;
    }

  

    //@CachePut(value = "productPriceCache", key = "#id")
    public PriceDetails updateProductPrice(ObjectId id,Product newProduct) throws MongoException{
    	 logger.info("updateProductPrice:  "+id);
        PriceDetails newProductPrice=newProduct.getPriceInfo();
        logger.info("newProductPrice:  "+newProductPrice);
        newProductPrice.setId(id);
        PriceDetails pd = priceDetailsRepository.findById(id);
       
            newProductPrice=priceDetailsRepository.save(newProduct.getPriceInfo());
            logger.info("newProductPrice***:  "+newProductPrice);
        return newProductPrice;
    }
/** This method can be called if the restapi url returned products **/
    
    private String getProductDesc(ObjectId id) throws IOException {
        String name = "";
        logger.info("in getProductDesc of service class ***");
        String url=	env.getProperty("restUrl1")+id.toHexString()+env.getProperty("restUrl2");
        ResponseEntity<String> response= restTemplate.getForEntity(url,String.class);
       // String jText = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";
       // response="{\"id":13860428,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}"
        	//	ResponseEntity<String>	response= new ResponseEntity<String>(jText, HttpStatus.OK);
        		
        		ObjectMapper mapper = new ObjectMapper();
        JsonNode treeRoot=null;
        String jsonString=response.getBody();
        logger.info(response.toString());
        if(jsonString!=null||!"".equals(jsonString)) {
            try {
                treeRoot = mapper.readTree(jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (treeRoot.findValue("product") != null) {
                treeRoot = treeRoot.findValue("product");
                if (treeRoot.findValue("item") != null) {
                    treeRoot = treeRoot.findValue("item");
                    if (treeRoot.findValue("product_description") != null) {
                        treeRoot = treeRoot.findValue("product_description");
                        if (treeRoot.findValue("title") != null) {
                            name = treeRoot.findValue("title").asText();
                        }
                    }
                }
            }
        }
        logger.info("name is " + name);
        return name;
    }

	
	public Product addProductDetails(@Valid Product product) {
		productRepository.save(product);
		  return product;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
    
  

}
