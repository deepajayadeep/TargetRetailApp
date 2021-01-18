package com.target.retail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.target.retail.repository.PriceDetailsRepository;

@SpringBootApplication
public class TargetRetailApplication  {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Autowired
	PriceDetailsRepository priceDetailsRepository;
	
	@Autowired
	RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(TargetRetailApplication.class, args);
	}

	
}
