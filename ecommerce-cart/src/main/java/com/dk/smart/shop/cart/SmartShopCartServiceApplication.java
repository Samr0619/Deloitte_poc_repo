package com.dk.smart.shop.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class SmartShopCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartShopCartServiceApplication.class, args);
	}

}
