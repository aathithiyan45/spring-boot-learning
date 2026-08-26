package com.aathithiyan.spring_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder) {

        UserDetails user = User.builder()
                .username("aathi")
                .password(passwordEncoder.encode("aathi123"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                // REST API → disable CSRF for this learning setup
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Anyone can access employee GET APIs
                        .requestMatchers(
                                HttpMethod.GET,
                                "/employees/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // Only ADMIN can create
                        .requestMatchers(
                                HttpMethod.POST,
                                "/employees"
                        ).hasRole("ADMIN")

                        // Only ADMIN can update
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/employees/**"
                        ).hasRole("ADMIN")

                        // Only ADMIN can delete
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/employees/**"
                        ).hasRole("ADMIN")

                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )

                .httpBasic(withDefaults());

        return http.build();
    }
}