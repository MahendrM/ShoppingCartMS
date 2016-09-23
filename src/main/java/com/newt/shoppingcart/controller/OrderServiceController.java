package com.newt.shoppingcart.controller;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.shoppingcart.commonutils.NotificationService;
import com.newt.shoppingcart.commonutils.Productstatus;
import com.newt.shoppingcart.model.Orders;
import com.newt.shoppingcart.model.OrdersItems;
import com.newt.shoppingcart.model.ShoppingCart;
import com.newt.shoppingcart.model.ShoppingCartItems;
import com.newt.shoppingcart.model.SmartPhone;
import com.newt.shoppingcart.repository.OrderItemsRepository;
import com.newt.shoppingcart.repository.OrderRepository;
import com.newt.shoppingcart.repository.ShoppingCartItemsRepository;
import com.newt.shoppingcart.repository.ShoppingCartRepository;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/shoppingcart/OrderService")
public class OrderServiceController {
	
	@Autowired
	OrderItemsRepository orderItemsRepo;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	ShoppingCartRepository shoppingcartRepo;
	@Autowired
	ShoppingCartItemsRepository shoppingcartItemsRepo;

	@Autowired
	private NotificationService emailNotification;
	
	Orders order = new Orders();
	OrdersItems orderItems = new OrdersItems();
	Date date = new Date();
	/*Method used to Create the Order*/
	
	@RequestMapping(value = "create/{shoppingCartID}", method = RequestMethod.PUT,produces = "application/json")
	@ApiOperation(value = "Create Order")
	public String createOrder(@RequestBody int shoppingCartID) {
			
		boolean statusMessage = false;
		
		ShoppingCart shopcartDtls;
		ShoppingCartItems shopcartItmsDtls;
		
		try {
		
			shopcartDtls = shoppingcartRepo.findByShoppingCartId(shoppingCartID);
			
			shopcartItmsDtls = shoppingcartItemsRepo.findByShoppingCartId(shoppingCartID);
			
			if (shopcartDtls != null) {
				
				System.out.println("Shop Cart Dtls--->"+shopcartDtls.toString());
				//Move the value from Shopping cart to Order after that delete the value in Shopping Cart.
				order.setCustomerId(shopcartDtls.getCustomerId());
				order.setOrderType("PICKUP");
				order.setOrderStatus("Pending");
				order.setCancelDate(null);
				order.setOrderDate(new Date());
				orderRepo.save(order);
			}
				if (shopcartItmsDtls != null) {
			
					//Move the value from Shopping cart Items to Order Items after that delete the value in Shopping Cart Items.
						orderItems.setOrderId(order.getOrderId());
						orderItems.setProductId(shopcartItmsDtls.getProductId());
						orderItems.setProductTypeId(shopcartItmsDtls.getProductTypeId());						
						orderItems.setQuantity(shopcartItmsDtls.getQuantity());
						orderItems.setPrice(shopcartItmsDtls.getPrice());
						orderItemsRepo.save(orderItems);
				}
			
				System.out.println("Shop Cart Items Values--->"+shopcartItmsDtls.toString());
				
				System.out.println("ShopCart Values----->"+shopcartDtls.toString());
				
		shoppingcartItemsRepo.delete(shopcartItmsDtls);
		shoppingcartRepo.delete(shopcartDtls);
		emailNotification.sendNotification("ORDER STATUS","Thanks for placing your order. We are currently processing your order");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"Order Created Successfully!! ORDERID==>"+order.getOrderId();
	}
	
	/************************Method to Cancel Order*************************/
	
	@RequestMapping(value = "cancel/{orderID}", method = RequestMethod.PUT,produces = "application/json")
	@ApiOperation(value = "Cancel Order")
	public Map<String,String> cancelOrder(@RequestBody int orderID) {	
		
		HashMap<String, String> statusmsg = new HashMap< String,String>();
		try {
			order=orderRepo.findByOrderId(orderID);
			if (order != null) {
				
				if(order.getOrderStatus().equalsIgnoreCase(Productstatus.PENDING.toString())||order.getOrderStatus().equalsIgnoreCase(Productstatus.PROCESSING.toString())||order.getOrderStatus().equalsIgnoreCase(Productstatus.CANCELLED.toString()))
				{
					order.setOrderStatus("Cancelled");
					statusmsg.put("Status", "SUCCESS");
					statusmsg.put("Message", "Your Order #"+order.getOrderId()+"Cancelled Successfully");
					 
					emailNotification.sendNotification("ORDER STATUS","Your Order#"+orderID+"has been Cancelled on"+date.toString());
					orderRepo.save(order);					
				}else if(order.getOrderStatus().equalsIgnoreCase(Productstatus.SHIPPED.toString()))
				{					
						statusmsg.put("Status", "Failure");
						statusmsg.put("Message", "Order Already Shipped");
						emailNotification.sendNotification("ORDER STATUS","Your Order#"+orderID+"Could not be Cancelled It's Already"+Productstatus.SHIPPED.toString());
				}
				else if(order.getOrderStatus().equalsIgnoreCase(Productstatus.DELIVERED.toString()))
				{
					statusmsg.put("Status", "Failure");
					statusmsg.put("Message", "Order Already Delivered");
					emailNotification.sendNotification("ORDER STATUS","Your Order#"+orderID+"Could not be Cancelled It's Already"+Productstatus.DELIVERED.toString());
				}
				
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusmsg;
	}
	
	/************************Method to Get Price of the Order*************************/	

	@RequestMapping(value = "getPrice/{orderID}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get Order Price")
	public float getOrderPrice(@PathVariable int orderID) {

		float price = 0;
		orderItems = orderItemsRepo.findByOrderId(orderID);

		if (order != null) {
			price = orderItems.getPrice();
			return price;

		}
		System.out.println("GET PRICE VAULE--->" + price);
		return price;
	}

/*	@RequestMapping(value = "additem/{customerId}/{productTypeId}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "add Item")
	public int addItem (@PathVariable int customerId,@PathVariable String productTypeId) {

		ShoppingCart shopCart=shoppingcartRepo.findByCustomerId(customerId);
		
		if (shopCart != null) {

			if (shopCart.getCustomerId() > 0) {			//IF Customer is Exist
				
				ShoppingCartItems shoppingCartIems = new ShoppingCartItems();	//Declare this Instance Globally
				
				shoppingCartIems.setShoppingCartId(shopCart.getShoppingCartId());	//Like this u can save other values in shoppingCartItems table...

				shoppingcartItemsRepo.save(shoppingCartIems);
				
			}else
			{
			
				
				shopCart.setCustomerId(customerId);
				shopCart.setStatus("PENDING");				
				shoppingcartRepo.save(shopCart);
				
				//	Create new cart for customer
				
			}
			
			
		}

		return 0;
			}*/

	
	
	
	
	
}
