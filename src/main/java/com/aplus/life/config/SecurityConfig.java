package com.aplus.life.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        var userDetailsService = new org.springframework.security.provisioning.InMemoryUserDetailsManager();
        var admin = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin123")
            .roles("ADMIN")
            .build();
        var badmin = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
            .username("badmin")
            .password("badmin123")
            .roles("BADMIN")
            .build();
        var user = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
            .username("user")
            .password("user123")
            .roles("USER")
            .build();
        userDetailsService.createUser(admin);
        userDetailsService.createUser(badmin);
        userDetailsService.createUser(user);
        return userDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/static/**", "/templates/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/badmin/**").hasRole("BADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .permitAll()
            )
            .logout(org.springframework.security.config.annotation.web.configurers.LogoutConfigurer::permitAll)
            .sessionManagement(session -> session
                .invalidSessionUrl("/login?invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
            );
        return http.build();
    }
}
