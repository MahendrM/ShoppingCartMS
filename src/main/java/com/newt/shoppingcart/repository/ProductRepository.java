package com.newt.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.newt.shoppingcart.model.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	public long count();

	public Product findByproductName(String productName);

	public Product findByProductId(int productId);
}
