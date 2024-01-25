//package com.dk.smart.shop.user.security;
//
//import java.io.IOException;
//
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//
//@Service
//public class AuthfilterService extends OncePerRequestFilter {
//	
//	private JWTService jwtService;
//	
//	private UserDetailsService userDetailService;
//	
//	private AuthenticationManager authenticationManager;
//	
//	public AuthfilterService() {
//		
//	}
//	
//
//	public AuthfilterService(JWTService jwtService,  UserDetailsService userDetailService) {
//		this.jwtService = jwtService;
//		this.userDetailService = userDetailService;
//	}
//
////	@Override
////	protected void doFilterInternal(@NonNull HttpServletRequest request,
////									@NonNull HttpServletResponse response, 
////									@NonNull FilterChain filterChain)
////									throws ServletException, IOException {
////		
////		final String authHeader = request.getHeader("Authorization");
////		
////		if(authHeader == null && !authHeader.startsWith("Bearer ")) {
////			filterChain.doFilter(request, response);
////			return;
////		}
////		
////		String jwt = authHeader.substring(7);
////		String userName = jwtService.extractUsername(jwt);
////		
////		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////			UserDetails userDetails = userDetailService.loadUserByUsername(userName);
////			if(jwtService.isTokenValid(userName, userDetails)) {
////				
////				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
////						userDetails, null, userDetails.getAuthorities());
////				
////				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////				 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
////				
////			}
////		}
//	
//	public AuthfilterService(AuthenticationManager authenticationManager) {
//		this.authenticationManager = authenticationManager;
//	}
//
//	@Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//        String jwt;
//        String username;
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // extract JWT
//        jwt = authHeader.substring(7);
//
//        // extract username from JWT
//        username = jwtService.extractUsername(jwt);
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailService.loadUserByUsername(username);
//            System.out.println("userDetails : " + userDetails);
//            if(jwtService.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                  userDetails,
//                  null,
//                  userDetails.getAuthorities()
//                );
//
//                authenticationToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//	
//}
