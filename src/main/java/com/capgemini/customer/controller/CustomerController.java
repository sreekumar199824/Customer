package com.capgemini.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customer.dto.DealerProductInfoBean;
import com.capgemini.customer.dto.OrderDetails;
import com.capgemini.customer.dto.ResponseClass;
import com.capgemini.customer.dto.UserInfoBean;
import com.capgemini.customer.service.CustomerService;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*",exposedHeaders="Access-Control-Allow-Origin")
public class CustomerController {

	@Autowired
	private CustomerService service;
	

	@PostMapping(path = "/buyProduct",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass buyProduct(@RequestBody UserInfoBean bean) {
		ResponseClass resp = new ResponseClass();
		OrderDetails order = service.buyProduct(bean);
		if(order!=null) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Order Placed Successfully");
			resp.setOrder(order);
			service.autoBuy();
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Placing Order Unsuccessfull");
			return resp;
		}
	}
	
	@GetMapping(path = "/getOrder",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass getOrder(@RequestParam("orderId")int orderId ) {
		ResponseClass resp = new ResponseClass();
		OrderDetails order = service.getOrderDetails(orderId);
		if(order!=null) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Order Found");
			resp.setOrder(order);
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Order Not Found");
			return resp;
		}
	}
	
	@GetMapping(path = "/getDealerProds",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseClass getManufacturersProducts() {
		ResponseClass resp = new ResponseClass();
		List<DealerProductInfoBean> products = service.getProds();
		if(products!=null) {
			resp.setStatusCode(201);
			resp.setMessage("Success");
			resp.setDescription("Products Found");
			resp.setDealerProds(products);
			return resp;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Failed");
			resp.setDescription("Products Not Found");
			return resp;
		}
	}
	
}
