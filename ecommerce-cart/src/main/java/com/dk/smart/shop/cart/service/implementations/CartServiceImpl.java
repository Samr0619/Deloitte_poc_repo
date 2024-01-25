package com.dk.smart.shop.cart.service.implementations;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.dk.smart.shop.cart.entity.Product;
import com.dk.smart.shop.cart.entity.UserCart;
import com.dk.smart.shop.cart.repository.CartRepository;
import com.dk.smart.shop.cart.repository.UserCartRepository;
import com.dk.smart.shop.cart.service.CartService;
import com.dk.smart.shop.cart.utils.JsonToJavaValue;

import jakarta.transaction.Transactional;


@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserCartRepository userCartRepository;

	@Override
	@Transactional
	public Product addProductToCart(Product product) {
		
		double totalPrice = product.getPrice() * product.getNoOfProducts();
		
		Optional<Product> optional = cartRepository.findByProductId(product.getProductId());
		
		if(optional.isPresent()) {
			return product;
		}
		
		Product newProduct = new Product(product.getProductId(), product.getProductName(), product.getBrandName()
								, product.getSubCategory(), product.getPrice(), product.getNoOfProducts()
								, totalPrice, product.getImage());
		
		UserCart userCart = new UserCart();
		Boolean isInCart = false;
		
		if(newProduct != null) {
			cartRepository.save(newProduct);
			isInCart = true;
			
			userCart.setProductId(product.getProductId());
			userCart.setProductName(product.getProductName());
			userCart.setInCart(isInCart);
			userCartRepository.save(userCart);
		}
		return newProduct;
	}

	@Override
	public List<Product> getAllProductsFromCart() {
		
		return cartRepository.findAll();
	}

	@Override
	public UserCart findIsInCart(Long product_id) {
		
		Optional<UserCart> optionalUserCart = userCartRepository.findByProductId(product_id);
		if(optionalUserCart.isPresent()) {
			return optionalUserCart.get();
		}
		UserCart userCart = new UserCart();
		userCart.setInCart(false);
		return userCart;
	}

	@Override
	@Transactional
	public String deleteByProductId(Long productId) {
		
		cartRepository.deleteByProductId(productId);
		userCartRepository.deleteByProductId(productId);
		return "Product Deleted Successfully!!!";
	}

	@Override
	public String updateById(Long productId, Map<String, Object> productRequestFields) {
		
		Optional<Product> optionalProduct = cartRepository.findByProductId(productId);
		System.out.println("optionalProduct - " + optionalProduct);
		
		if(optionalProduct.isPresent()) {
			Product existingProduct = optionalProduct.get();
			System.out.println("existingProduct - " + existingProduct);
			
			productRequestFields.forEach((key, value) -> {
				
				String fieldName = "";
				
				if(key.contains("_")){
					/**
					 * Calling 'JsonToJavaValue.convertToJavaStyle(key)' method to convert json field to java style(camelCase)
					 * EX: "no_of_products" to "noOfProducts"
					 * If this conversion not performed 'Field' value will be null and a NullPointerException will occur
					 * 
					 */
					System.out.println("In if block...");
					fieldName = JsonToJavaValue.convertToJavaStyle(key);
				}
				else {
					System.out.println("In else block...");
					fieldName = key;
				}
				
				System.out.println("Key - " + fieldName);
				Field field = ReflectionUtils.findField(Product.class, fieldName);
				System.out.println("field - " + field);
				field.setAccessible(true);
				ReflectionUtils.setField(field, existingProduct, value);
				System.out.println("Value - " + value);
				cartRepository.save(existingProduct);
			});
		}
		return "Product Updated Successfully!!!";
	}

}
