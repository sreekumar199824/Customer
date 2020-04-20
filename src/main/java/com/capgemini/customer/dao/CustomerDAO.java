package com.capgemini.customer.dao;

import java.util.List;

import com.capgemini.customer.dto.*;


public interface CustomerDAO {
	public OrderDetails buyProduct(UserInfoBean dealer);
	public OrderDetails getOrderDetails(int id);
	public boolean checkEmailAvailability(String email);
	public void autoBuy();
	public List<DealerProductInfoBean> getProds();
}
