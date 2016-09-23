package com.newt.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.newt.shoppingcart.model.Orders;
import com.newt.shoppingcart.model.SmartPhone;
@Repository
public interface OrderRepository extends CrudRepository<Orders, String>{

	 public Orders findByOrderId(int orderID);
	 public Orders findByCustomerId(int customerID);
	 /*public SmartPhone findByProductId(int productId);
	 public long count();*/
}
