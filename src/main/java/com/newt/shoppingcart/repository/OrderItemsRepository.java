package com.newt.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.newt.shoppingcart.model.OrdersItems;
@Repository
public interface OrderItemsRepository extends CrudRepository<OrdersItems, String>{

	public OrdersItems findByOrderId(int orderID);

	 /*public SmartPhone findByBrand(String brand);
	 public SmartPhone findByProductId(int productId);
	 public long count();*/
}
