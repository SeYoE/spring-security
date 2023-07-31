package com.tangerine.springsecuritymasterclass.configures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/me")
                        .hasAnyRole("USER", "ADMIN")
                        .anyRequest()
                        .permitAll())
                .formLogin(login -> login
                        .defaultSuccessUrl("/login")
                        .permitAll());
        return http.build();
    }

//    @Bean
//    @Order(0)
//    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/assets/**")
//                        .hasAnyRole("USER", "ADMIN")
//                        .anyRequest()
//                        .permitAll())
//                .build();
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/assets/**");
    }

}