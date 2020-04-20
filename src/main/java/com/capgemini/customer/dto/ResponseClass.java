package com.capgemini.customer.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseClass {
	
	private int statusCode;
	private String message;
	private String description;
	private List<UserInfoBean> users;
	private List<ProductInfoBean> products;
	private List<OrderDetails> orders;
	private OrderDetails order;
	private UserInfoBean user;
	private DealerProductInfoBean dealerProd;
	private List<DealerProductInfoBean> dealerProds;
	private List<CartInfoBean> items;
}
