package com.gateway.filter;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
	
	@Autowired private RouteValidators routeValidators;
	@Autowired private RestTemplate template;
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	
	
	public static class Config{
		
	}


	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		return ((exchange,chain)->{
			
			if(routeValidators.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing Autherization Token");
				}
				
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				if(authHeader!=null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}
				
				try {
					String res = template.getForObject("http://localhost:8006/route/validate?token="+authHeader, String.class);
					System.out.println("Res ::: "+res);
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					//throw new RuntimeException("UnAuthorize Access!!!!!");
				}
			}
			
			return chain.filter(exchange);
		});
	}
}
