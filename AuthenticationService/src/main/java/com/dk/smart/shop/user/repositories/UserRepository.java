package com.dk.smart.shop.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dk.smart.shop.user.entities.User;


/**
 * @author DurgaKalyan
 * @version 1.0
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	Optional<User> findByEmail(String userName);
}