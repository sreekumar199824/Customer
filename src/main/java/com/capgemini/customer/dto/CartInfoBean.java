package com.capgemini.customer.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "cart_info")
public class CartInfoBean {
	
	@Id
	@Column
	@GeneratedValue
	private int itemId;
	
	@Column
	private String role;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId",referencedColumnName = "userId")
	private UserInfoBean user;
	
	@Column
	private int itemProductId;
	
	@Column
	private int quantity;
	
	@Transient
	private ProductInfoBean product;
	
	@Transient
	private DealerProductInfoBean dealerProd;
	
}
