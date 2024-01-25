package com.dk.smart.shop.cart.controllers;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dk.smart.shop.cart.dto.OrderDto;
import com.dk.smart.shop.cart.entity.Product;
import com.dk.smart.shop.cart.entity.UserCart;
import com.dk.smart.shop.cart.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/smart/shop/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/add/product")
	public ResponseEntity<Product> addToCart(@RequestBody Product product){
		
		System.out.println("Request - " + product);
		Product addedProduct = cartService.addProductToCart(product);
		return ResponseEntity.status(HttpStatus.OK).body(addedProduct);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		List<Product> allProductsInCart = cartService.getAllProductsFromCart();
		return ResponseEntity.status(HttpStatus.OK).body(allProductsInCart);
	}
	
	@GetMapping("/get/status/{product_id}")
	public ResponseEntity<UserCart> getUserCartStatus(@PathVariable Long product_id){
		
		UserCart userCart = cartService.findIsInCart(product_id);
		return ResponseEntity.status(HttpStatus.OK).body(userCart);
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteByProductId(@PathVariable Long productId){
		
		String message = cartService.deleteByProductId(productId);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@PatchMapping("/update/{productId}")
	public ResponseEntity<String> updateByProductId(@PathVariable Long productId, @RequestBody Map<String, Object> productRequestFields){
		
		String message = cartService.updateById(productId, productRequestFields);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@PostMapping("/buy")
	@CircuitBreaker(name = "buyCircuitBreaker", fallbackMethod = "fallbackBuy")
	public ResponseEntity<?> buyFromCart(@RequestBody OrderDto order) throws JsonProcessingException{
		String orderResultAsJsonStr = null;
		 JSONObject jsonobj =null;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(order);
		
		 HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);

		 orderResultAsJsonStr = restTemplate.postForObject("http://ORDER-SERVICE/orders/createOrder", request,
				 String.class);
		 
		 jsonobj = new JSONObject(orderResultAsJsonStr);
		 
		return ResponseEntity.ok(jsonobj.toString());
	}
	
	
	public ResponseEntity<?> fallbackBuy(OrderDto dto,Throwable throwable){
		return ResponseEntity.ok("Service Not Available");
	}
}
