package com.target.retail.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	@Id
    private ObjectId id;
    private String name;
    @JsonProperty(value = "current_price")
    private PriceDetails priceInfo;
 

    public String getId() { return id.toHexString(); }
    public Product() {
    }

    public void setId(ObjectId objectId) {
        this.id = objectId;
    }

    public Product(ObjectId id, String name, PriceDetails priceInfo) {
        this.id = id;
        this.name = name;
        this.priceInfo = priceInfo;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriceDetails getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(PriceDetails priceInfo) {
        this.priceInfo = priceInfo;
    }

}
