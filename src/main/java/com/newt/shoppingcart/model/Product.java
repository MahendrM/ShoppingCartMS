package com.newt.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	
	@Id
	private int Id;
	private String productTypeId;
	
	/*@OneToMany
	  @JoinColumn(name = "productTypeId")
	  private Set<Smartphone> smartphone;*/
}
