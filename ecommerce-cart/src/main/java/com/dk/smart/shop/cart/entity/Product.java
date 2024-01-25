package com.dk.smart.shop.cart.entity;

import java.util.Arrays;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "cart")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private Long productId;
	
	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;

	@Column(name = "brand_name")
	@JsonProperty("brand_name")
	private String brandName;
	
	@Column(name = "sub_category")
	@JsonProperty("sub_category")
	private String subCategory;

	private double price;
	
	@Column(name = "number_of_products")
	@JsonProperty("no_of_products")
	private int noOfProducts;
	
	@Column(name = "total_price")
	@JsonProperty("total_price")
	private double totalPrice;
	
	private byte [] image;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getNoOfProducts() {
		return noOfProducts;
	}

	public void setNoOfProducts(int noOfProducts) {
		this.noOfProducts = noOfProducts;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	public Product() {
		
	}
	
	public Product(Long productId, String productName, String brandName, String subCategory, double price,
			int noOfProducts, double totalPrice, byte[] image) {
		this.productId = productId;
		this.productName = productName;
		this.brandName = brandName;
		this.subCategory = subCategory;
		this.price = price;
		this.noOfProducts = noOfProducts;
		this.totalPrice = totalPrice;
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", productId=" + productId + ", productName=" + productName + ", brandName="
				+ brandName + ", subCategory=" + subCategory + ", price=" + price + ", noOfProducts=" + noOfProducts
				+ ", totalPrice=" + totalPrice + ", image=" + Arrays.toString(image) + "]";
	}

}
