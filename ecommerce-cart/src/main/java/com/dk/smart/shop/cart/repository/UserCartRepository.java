package com.dk.smart.shop.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dk.smart.shop.cart.entity.UserCart;


@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long>{

	Optional<UserCart> findByProductId(Long product_id);

	@Modifying
	@Query(value = "DELETE FROM user_cart WHERE product_id = :productId", nativeQuery = true)
	void deleteByProductId(@Param("productId") Long productId);


}
