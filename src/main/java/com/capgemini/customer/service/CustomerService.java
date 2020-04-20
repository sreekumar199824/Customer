package com.capgemini.customer.service;

import java.util.List;

import com.capgemini.customer.dto.DealerProductInfoBean;
import com.capgemini.customer.dto.OrderDetails;
import com.capgemini.customer.dto.UserInfoBean;

public interface CustomerService {
	public OrderDetails buyProduct(UserInfoBean dealer);
	public OrderDetails getOrderDetails(int id);
	public boolean checkEmailAvailability(String email);
	public void autoBuy();
	public List<DealerProductInfoBean> getProds();
}
