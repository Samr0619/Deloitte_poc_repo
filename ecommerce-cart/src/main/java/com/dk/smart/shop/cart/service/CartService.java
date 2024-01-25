package com.dk.smart.shop.cart.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dk.smart.shop.cart.entity.Product;
import com.dk.smart.shop.cart.entity.UserCart;


@Service
public interface CartService {

	public Product addProductToCart(Product product);
	
	public List<Product> getAllProductsFromCart();
	
	public UserCart findIsInCart(Long product_id);
	
	public String deleteByProductId(Long productId);

	public String updateById(Long productId, Map<String, Object> productRequestFields); 
}
