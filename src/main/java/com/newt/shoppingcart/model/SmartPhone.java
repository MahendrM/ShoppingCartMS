package com.newt.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@Entity
public class SmartPhone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;

	private String productTypeId;

	private String shortDescription;

	private String model;

	private String brand;

	private String size;

	private float displaySize;

	private String color;

	private float weight;

	private String carrierType;

	private String networkCapability;

	private int batteryPower;

	private String operatingSystem;

	private String processor;

	private float internalMemory;

	private float ram;

	private int frontCamera;

	private int rearCamera;

	private float price;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public float getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(float displaySize) {
		this.displaySize = displaySize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getCarrierType() {
		return carrierType;
	}

	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}

	public String getNetworkCapability() {
		return networkCapability;
	}

	public void setNetworkCapability(String networkCapability) {
		this.networkCapability = networkCapability;
	}

	public int getBatteryPower() {
		return batteryPower;
	}

	public void setBatteryPower(int batteryPower) {
		this.batteryPower = batteryPower;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public float getInternalMemory() {
		return internalMemory;
	}

	public void setInternalMemory(float internalMemory) {
		this.internalMemory = internalMemory;
	}

	public float getRam() {
		return ram;
	}

	public void setRam(float ram) {
		this.ram = ram;
	}

	public int getFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(int frontCamera) {
		this.frontCamera = frontCamera;
	}

	public int getRearCamera() {
		return rearCamera;
	}

	public void setRearCamera(int rearCamera) {
		this.rearCamera = rearCamera;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
