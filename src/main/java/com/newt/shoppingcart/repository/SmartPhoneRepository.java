package com.newt.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.newt.shoppingcart.model.SmartPhone;
@Repository
public interface SmartPhoneRepository extends CrudRepository<SmartPhone, String>{

	 public SmartPhone findByBrand(String brand);
	 public SmartPhone findByProductId(int productId);
	 public long count();
}
