package com.target.retail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.io.Serializable;

@Document(collection = "prices")
public class PriceDetails implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @JsonIgnore
    private ObjectId id;
    private  double value;
    private  String currency_code;

    public String getId() {
        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public PriceDetails(ObjectId id, double value, String currency_code) {
        this.id = id;
        this.value = value;
        this.currency_code = currency_code;
    }

    public PriceDetails() {
    }

   

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
