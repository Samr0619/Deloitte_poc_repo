package com.dk.smart.shop.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/**","/smart/shop/user/validate").permitAll()
                .requestMatchers(HttpMethod.GET, "/smart/shop/user/test")
                .hasAnyAuthority("USER","ADMIN")
                .requestMatchers(HttpMethod.GET, "/smart/shop/user/test/admin")
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                )
            .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
//        http.csrf(AbstractHttpConfigurer::disable)
//        .authorizeHttpRequests(auth ->
//        auth.requestMatchers("/smart/shop/user/**").authenticated().requestMatchers("/user/**").permitAll()
//        .anyRequest()
//        .authenticated());
//        http.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
    }


}





















//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
////    private AuthfilterService authFilterService;
////    private AuthenticationProvider authenticationProvider;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http,  JWTService jwtservice, UserDetailsService userDetailsService) throws Exception {
//    	
////    	AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
////    	AuthenticationManager authenticationManager = builder.build();
//    	
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/smart/shop/user/**")
//                        .permitAll().anyRequest().authenticated());
//                http.addFilterBefore(new AuthfilterService(jwtservice, userDetailsService), 
//                		UsernamePasswordAuthenticationFilter.class)
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////        .anyRequest()
////        .authenticated())
////.sessionManagement(session -> session
////		.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////                .authenticationProvider(authenticationProvider)
////                .addFilterBefore(authFilterService, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//}
