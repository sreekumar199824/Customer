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
import lombok.ToString.Exclude;

@Entity
@Data
@Table(name = "dealer_prods")
public class DealerProductInfoBean {
	
	@Id
	@GeneratedValue
	@Column
	private int dealersProductId;
	
	@Column
	private double sellingPrice;
	
	@Column
	private int quantity;

	@Column
	private int productId;
	
	@Column
	private int minimumQuantity;
	
	
	@Column
	private String imageUrl;
	
	@Column
	private String productName;
	
	
	@Column
	private String manufacturerName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="dealerId",referencedColumnName = "userId")
	@Exclude
	private UserInfoBean user ;
	
	@Transient
	private ProductInfoBean product;
}
