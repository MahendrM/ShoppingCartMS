package com.newt.shoppingcart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.newt.shoppingcart.model.ShoppingCart;
import com.newt.shoppingcart.model.SmartPhone;
@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String>{

	 public ShoppingCart findByShoppingcartId(int brand);
	 public ShoppingCart findByProductId(int productId);
	 public List<ShoppingCart>findByCustomerId(int brand);
	 /*public long count();*/
}
