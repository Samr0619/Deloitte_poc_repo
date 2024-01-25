package com.dk.smart.shop.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dk.smart.shop.user.entities.RefreshToken;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String>{

	Optional<RefreshToken> findByRefreshToken(String refreshToken);
}