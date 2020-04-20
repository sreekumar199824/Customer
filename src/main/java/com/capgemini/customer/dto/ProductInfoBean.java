package com.capgemini.customer.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

@Data
@Entity
@Table(name = "product_info")
public class ProductInfoBean {
	
	@Id
	@GeneratedValue
	@Column
	private int productId;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private double productCost;
	
	
	@Column 
	private String description;
	 
	
	
	@Column
	private String imageUrl;
	
	@Column
	private int quantity;
	
	
	@Column
	private String manufacturerName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId",referencedColumnName = "userId")
	@EqualsAndHashCode.Exclude 
	@Exclude
	private UserInfoBean users ;
}
