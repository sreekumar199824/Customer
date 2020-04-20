package com.capgemini.customer.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
import lombok.ToString.Exclude;

@SuppressWarnings("unused")
@Entity
@Data
@Table(name = "orders_info")
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int orderId;
	
	@Column(nullable = false)
	private String role;
	
	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private String paymentType;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column
	private int productId;
	
	
	
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate deliveredOn ;
	
	@Column
	@JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfOrder;
	
	@Column
	@JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfDelivery;
	
	@Column(nullable = false)
	private String productName;
	
	@Column
	private String status;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId",referencedColumnName = "userId")
	@Exclude
	private UserInfoBean user;
	
	@Transient
	private ProductInfoBean product;
	
}
