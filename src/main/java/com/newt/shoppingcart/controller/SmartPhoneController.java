package com.newt.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.shoppingcart.model.SmartPhone;
import com.newt.shoppingcart.repository.SmartPhoneRepository;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/shoppingcart")
public class SmartPhoneController {
	private final SmartPhoneRepository smartphoneRepo;

	@Autowired
	public SmartPhoneController(SmartPhoneRepository smartphoneRepo) {
		this.smartphoneRepo = smartphoneRepo;
	}

	/*Method used to Add the phone details and return productid*/
	@ApiOperation(value = "Register Smart Phone")
	@RequestMapping(method = RequestMethod.POST)
	public SmartPhone addSmartPhone(@RequestBody SmartPhone addphone) {
		
		return smartphoneRepo.save(addphone);
	}

	/*Method used to update the phone details based on productid*/
	
	@RequestMapping(value = "update/{productid}", method = RequestMethod.PUT)
	public String updateSmartPhone(@RequestBody SmartPhone updatephone) {
			boolean statusMessage = false;
		try {
		
			SmartPhone details = smartphoneRepo.findByProductId(updatephone.getProductId());			
			if (details != null) {
				if (details.getProductId()>0) {
					
					details.setShortDescription(updatephone.getShortDescription());
					details.setModel(updatephone.getModel());
					details.setBrand(updatephone.getBrand());
					details.setSize(updatephone.getSize());
					details.setDisplaySize(updatephone.getDisplaySize());
					details.setColor(updatephone.getColor());
					details.setWeight(updatephone.getWeight());
					details.setCarrierType(updatephone.getCarrierType());
					details.setNetworkCapability(updatephone.getNetworkCapability());
					details.setBatteryPower(updatephone.getBatteryPower());
					details.setOperatingSystem(updatephone.getOperatingSystem());
					details.setProcessor(updatephone.getProcessor());
					details.setInternalMemory(updatephone.getInternalMemory());
					details.setRam(updatephone.getRam());
					details.setFrontCamera(updatephone.getFrontCamera());
					details.setRearCamera(updatephone.getRearCamera());
					details.setPrice(updatephone.getPrice());
					smartphoneRepo.save(details);
					statusMessage = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return"Updated Successfully STATUS="+statusMessage;
	}

	
	/*Method used to Search  the phone details based on productid*/
	
	@RequestMapping(value = "search/{productid}", method = RequestMethod.GET)
	@ApiOperation(value = "Find By Car Name")
	public SmartPhone findPhone(@PathVariable int productid) {
		return smartphoneRepo.findByProductId(productid);
	}

	/*list all phones in SmartPhoneTable*/
	
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "List All Phones in Smart Phone Table")
	public Iterable listPhones() {
		return smartphoneRepo.findAll();
	}

	
	/*Method used to delete the phone details based on productid*/
	
	@RequestMapping(value = "delete/{productid}", method = RequestMethod.GET)
	public String deleteSmartPhone(@PathVariable int productid) {
		try {
			SmartPhone deleted = smartphoneRepo.findByProductId(productid);
			smartphoneRepo.delete(deleted);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Deleted Successfully";
	}
	
	
}
