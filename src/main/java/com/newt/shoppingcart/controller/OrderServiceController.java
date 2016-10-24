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

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.newt.shoppingcart.commonutils.NotificationService;
import com.newt.shoppingcart.commonutils.Productstatus;
import com.newt.shoppingcart.commonutils.ShoppingCartstatus;
import com.newt.shoppingcart.model.ShoppingCart;
import com.newt.shoppingcart.model.ShoppingCartItems;
import com.newt.shoppingcart.repository.ShoppingCartItemsRepository;
import com.newt.shoppingcart.repository.ShoppingCartRepository;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/shoppingcart/OrderService")
public class OrderServiceController {
	
	@Autowired
	ShoppingCartRepository shoppingcartRepo;
	@Autowired
	ShoppingCartItemsRepository shoppingcartItemsRepo;
	
	@Autowired
	private NotificationService emailNotification;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	
	Date date = new Date();
	
	@RequestMapping(value = "create/{customerId}/{customerName}/{productId}/{productName}/{price}/{productdesc}", method = RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Create Order")
	public Map<String,String> createOrder(@PathVariable int customerId,@PathVariable String customerName,@PathVariable int productId,@PathVariable String productName,
			@PathVariable float price,@PathVariable String productdesc) {
			
		HashMap<String, String> statusmsg = new HashMap<String,String>();
		float totalcost = 0;
			try {
				ShoppingCart shopcartDtlsnew = new ShoppingCart();

					shopcartDtlsnew.setCustomerId(customerId);
					shopcartDtlsnew.setCreatedDate(new Date());
					shopcartDtlsnew.setStatus(ShoppingCartstatus.ACTIVE.toString());
					shopcartDtlsnew.setProductId(productId);
					shopcartDtlsnew=shoppingcartRepo.save(shopcartDtlsnew);
					ShoppingCartItems shopcartItmsDtlsnew = new ShoppingCartItems();

					shopcartItmsDtlsnew.setShoppingcartId(shopcartDtlsnew.getShoppingcartId());
					shopcartItmsDtlsnew.setProductName(productName);
					shopcartItmsDtlsnew.setProductId(productId);
					shopcartItmsDtlsnew.setPrice(price);
					shopcartItmsDtlsnew.setProductDesc(productdesc);
					shopcartItmsDtlsnew.setCustomerId(customerId);
					shopcartItmsDtlsnew.setStatus(ShoppingCartstatus.ACTIVE.toString());
					shopcartItmsDtlsnew=shoppingcartItemsRepo.save(shopcartItmsDtlsnew);
			//		emailNotification.sendNotification("ORDER STATUS","Your Order#"+shopcartDtls.getShoppingCartId()+"has Created Successfully"+date.toString());
		
			//Already Ordered Items list
					List<ShoppingCartItems>shopcartItmsDtlsList;
					List<ShoppingCartItems>shopcartItemsresult = new ArrayList<ShoppingCartItems>();
					shopcartItmsDtlsList= shoppingcartItemsRepo.findByCustomerId(customerId);					
					if (shopcartItmsDtlsList != null) {
						for (ShoppingCartItems shoppingCartItems : shopcartItmsDtlsList) {
							if(shoppingCartItems.getStatus().equalsIgnoreCase(ShoppingCartstatus.ACTIVE.toString())){
								shopcartItemsresult.add(shoppingCartItems);
								totalcost=totalcost+shoppingCartItems.getPrice();
								
							}
						}				
					}
					//Already Ordered Items list
					statusmsg.put("MESSAGE",""+totalcost);
				
		
			} catch (Exception e) {
				e.printStackTrace();
				statusmsg.put("STATUS","FAILURE");
				statusmsg.put("MESSAGE",e.toString());
			}

		
		return statusmsg;
	}
	
	@RequestMapping(value = "get/{shopcartItmId}", method = RequestMethod.GET,produces = "application/json")
	@ApiOperation(value = "Get ShoppingCart Details")
	public ShoppingCartItems getOrder(@PathVariable int shopcartItmId) {	
		try {
			ShoppingCartItems shopcartItmsDtls = new ShoppingCartItems();
			
			shopcartItmsDtls=shoppingcartItemsRepo.findByShoppingcartitemsId(shopcartItmId);
			if (shopcartItmsDtls != null) {
				
				return shopcartItmsDtls;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "get/shoppingcart/{shopcartId}", method = RequestMethod.GET,produces = "application/json")
	@ApiOperation(value = "Get ShoppingCart Details")
	public ShoppingCart  getByShopcartId(@PathVariable int shopcartId) {	
		try {
			ShoppingCart shopcartDtls = new ShoppingCart();
			shopcartDtls=shoppingcartRepo.findByShoppingcartId(shopcartId);
			
			if (shopcartDtls != null) {
				
				return shopcartDtls;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "get/productList/{customerId}", method = RequestMethod.GET,produces = "application/json")
	@ApiOperation(value = "Get ShoppingCart Details")
	public List<ShoppingCartItems> orderCheckout(@PathVariable int customerId) {	
		try {
			List<ShoppingCartItems>shopcartItmsDtlsList;
			List<ShoppingCartItems>shopcartItemsresult = new ArrayList<ShoppingCartItems>();
			shopcartItmsDtlsList= shoppingcartItemsRepo.findByCustomerId(customerId);
			
			if (shopcartItmsDtlsList != null) {
				for (ShoppingCartItems shoppingCartItems : shopcartItmsDtlsList) {
					if(shoppingCartItems.getStatus().equalsIgnoreCase(ShoppingCartstatus.ACTIVE.toString())){
						shopcartItemsresult.add(shoppingCartItems);
					}
				}		
				return shopcartItemsresult;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "deleteOrder/{shopcartItemsID}/{customerId}/{productId}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "Delete Order")
	public Map<String,String>deleteOrder(@PathVariable int shopcartItemsID, @PathVariable int customerId,@PathVariable int productId) {
		HashMap<String, String> statusmsg = new HashMap< String,String>();
		try{
			ShoppingCart shopcartDtls = new ShoppingCart();
			ShoppingCartItems shopcartItmsDtls = new ShoppingCartItems();
			shopcartDtls = shoppingcartRepo.findByProductId(productId);
			if (shopcartDtls != null) {
				shoppingcartRepo.delete(shopcartDtls);
				
				shopcartItmsDtls = shoppingcartItemsRepo.findByProductId(productId);
				if (shopcartItmsDtls != null) {
				
					if(shopcartItmsDtls.getShoppingcartitemsId()==shopcartItemsID){
				
						shoppingcartItemsRepo.delete(shopcartItmsDtls);
				
						statusmsg.put("STATUS","SUCCESS");
						statusmsg.put("MESSAGE","YOUR ORDER DELETED SUCCESSFULLY");
					}					
				}
			}	
		}catch(Exception e){
			
			statusmsg.put("STATUS","FAILURE");
			statusmsg.put("MESSAGE",e.toString());
			
		}
		return statusmsg;
	}	
}
