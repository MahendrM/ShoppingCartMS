package com.newt.shoppingcart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.newt.shoppingcart.model.ShoppingCartItems;
import com.newt.shoppingcart.model.SmartPhone;
@Repository
public interface ShoppingCartItemsRepository extends CrudRepository<ShoppingCartItems, String>{

	 public ShoppingCartItems findByShoppingcartId(int shoppingCartID);
	 public ShoppingCartItems findByShoppingcartitemsId(int shoppingCartID);
	 public ShoppingCartItems findByProductId(int productId);
	 List<ShoppingCartItems> findByCustomerId(int customerId);
	 public ShoppingCartItems findByCustomerIdAndProductId(int customerId,int productId);
	 /*public long count();*/
}
