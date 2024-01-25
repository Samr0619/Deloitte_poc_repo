package com.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
public class RouteValidators {

	public static List<String> openEndpoints = List.of(
			"/eureka",
			//"/smart/shop/user/validate",
			"/route/validate",
			"/user/**"
			);
	
	public Predicate<ServerHttpRequest> isSecured = 
			request -> openEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
}
