package com.newt.shoppingcart.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.newt.shoppingcart.commonutils.NotificationService;
import com.newt.shoppingcart.commonutils.Productstatus;
import com.newt.shoppingcart.commonutils.ShoppingCartstatus;
import com.newt.shoppingcart.model.ProductList;
import com.newt.shoppingcart.model.ShoppingCart;
import com.newt.shoppingcart.model.ShoppingCartItems;
import com.newt.shoppingcart.repository.ShoppingCartItemsRepository;
import com.newt.shoppingcart.repository.ShoppingCartRepository;
import com.wordnik.swagger.annotations.ApiOperation;

@Component
public class KafkaController {
	
	@Autowired
	ShoppingCartRepository shoppingcartRepo;
	@Autowired
	ShoppingCartItemsRepository shoppingcartItemsRepo;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	//Method 1: This method is used to save the list of json values in a db using ObjectMapper.
	
	@PostConstruct
	public void consumeNumbersList() throws IOException  {
		try{
        ObjectMapper mapper = new ObjectMapper();
        List<ProductList> productList;
        KafkaConsumer<String, String> consumer = getConsumerConn();

        consumer.subscribe(Arrays.asList("product-details"));
        int timeouts = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(0);
            if (records.count() == 0) {
                timeouts++;
            } else {
                timeouts = 0;
            }            
            for (ConsumerRecord<String, String> record : records) {
   
            productList = mapper.readValue(record.value(), new TypeReference<List<ProductList>>(){});
					if (productList != null) {
							
						for (ProductList productdetails : productList) {
							updateShoppingcartStatus(productdetails.getProductStatus(), productdetails.getShoppingcartId());
						}
						
					}
            }
        
        }
        }catch (Exception e) {

        	e.printStackTrace();
		}
	}
	
//Obtain Kafka Method 2:
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void consumeNumbers() throws IOException  {
		try{
			
        ObjectMapper mapper = new ObjectMapper();
        
        KafkaConsumer<String, String> consumer = getConsumerConn();

        consumer.subscribe(Arrays.asList("product-details"));
        System.out.println("Kafka Test--->");
        int timeouts = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(0);
            if (records.count() == 0) {
                timeouts++;
            } else {
                timeouts = 0;
            }            
            for (ConsumerRecord<String, String> record : records) {
           	String prodStatus;
           	int shpcartId = 0;
           	
           	System.out.printf("TEST2----->Offset"+record.offset()+"Key"+record.key()+"Value"+record.value());
           	
                switch(record.topic()){
                 case "product-details":
                	 System.out.printf("TEST3");
                  JsonNode msg = mapper.readTree(record.value());
                  switch (msg.get("event").asText()) {
                    case "Product Status Changed":
                    	prodStatus = msg.get("productStatus").asText();
                    	shpcartId =  msg.get("shoppingcartId").asInt();
                     System.out.printf("Shopping Cart ID:"+shpcartId+"Status"+prodStatus);
                  //   updateShoppingcartStatus(Productstatus.valueOf(prodStatus), shpcartId);
                  }
                }
            }
        }
        }catch (Exception e) {

        	e.printStackTrace();
		}
	}
	private void updateShoppingcartStatus(Productstatus valueOf, int shpcartId) {
		
		ShoppingCart shopCart;
		ShoppingCartItems shopcartItems;
		shopCart=shoppingcartRepo.findByShoppingcartId(shpcartId);
		shopcartItems=shoppingcartItemsRepo.findByShoppingcartId(shpcartId);
		if(shopCart!=null &&shopcartItems!=null)
		{
			shopCart.setCustomerId(shopCart.getCustomerId());
			shopCart.setProductId(shopCart.getProductId());
			shopCart.setShoppingcartId(shopCart.getShoppingcartId());
			shopCart.setStatus(valueOf.name());
			shopCart.setCreatedDate(new Date());
			shoppingcartRepo.save(shopCart);
			System.out.println("Kafka Updated ShopCart details-->"+shopCart.toString());
			
			shopcartItems.setCustomerId(shopcartItems.getCustomerId());
			shopcartItems.setPrice(shopcartItems.getPrice());
			shopcartItems.setProductDesc(shopcartItems.getProductDesc());
			shopcartItems.setProductId(shopcartItems.getProductId());
			shopcartItems.setProductName(shopcartItems.getProductName());
			shopcartItems.setShoppingcartId(shopcartItems.getShoppingcartId());
			shopcartItems.setShoppingcartitemsId(shopcartItems.getShoppingcartitemsId());
			shopcartItems.setStatus(valueOf.name());
			shoppingcartItemsRepo.save(shopcartItems);
			System.out.println("Kafka Updated ShopCartItems details-->"+shopCart.toString());
			}
		
	}

	private KafkaConsumer<String, String> getConsumerConn() throws IOException {
		KafkaConsumer<String, String> consumer;
        try (InputStream props = Resources.getResource("shoppingcart.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            if (properties.getProperty("group.id") == null) {
                properties.setProperty("group.id", "group-" + new Random().nextInt(100000));
            }	
            consumer = new KafkaConsumer<>(properties);
        }
		return consumer;
	}
	
}
