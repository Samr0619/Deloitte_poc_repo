package com.dk.smart.shop.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dk.smart.shop.cart.entity.Product;


@Repository
public interface CartRepository extends JpaRepository<Product, Long>{

	@Query(value = "SELECT * FROM cart WHERE product_id =:productId", nativeQuery = true)
	Optional<Product> findByProductId(@Param(value = "productId") Long productId);

	@Modifying
	@Query(value = "DELETE FROM cart WHERE product_id = :productId", nativeQuery = true)
	void deleteByProductId(@Param("productId") Long productId);

}
