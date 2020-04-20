package com.capgemini.customer.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

@Data
@Entity
@Table(name = "user_info")
public class UserInfoBean {
	
	@Id
	@GeneratedValue
	@Column
	private int userId;
	
	@Column
	private String name;
	
	@Column
	private String role;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private long mobileNumber;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	@Exclude
	private List<OrderDetails> orders = new LinkedList<OrderDetails>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	@Exclude
	private List<CartInfoBean> items = new LinkedList<CartInfoBean>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "users")
	@EqualsAndHashCode.Exclude
	private List<ProductInfoBean> products = new ArrayList<ProductInfoBean>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<DealerProductInfoBean> dealersProds = new ArrayList<DealerProductInfoBean>();

}
