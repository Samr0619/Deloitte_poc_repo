//package com.dk.smart.shop.user.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import javax.sql.DataSource;
//
//@Configuration
//public class BeanConfig {
//
//    private final DataSource dataSource; // Inject your DataSource
//
//    public BeanConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
//        userDetailsManager.setDataSource(dataSource);
//        return userDetailsManager;
//    }
//}
