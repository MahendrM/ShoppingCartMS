package com.newt.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.newt.shoppingcart.model.ShoppingCartItems;
import com.newt.shoppingcart.model.SmartPhone;
@Repository
public interface ShoppingCartItemsRepository extends CrudRepository<ShoppingCartItems, String>{

	 public ShoppingCartItems findByShoppingCartId(int shoppingCartID);
	 /*public SmartPhone findByProductId(int productId);
	 public long count();*/
}
