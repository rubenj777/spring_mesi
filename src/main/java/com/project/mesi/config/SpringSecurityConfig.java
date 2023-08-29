package com.project.mesi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(
                                "/",
                                "/subscribe",
                                "/products/**",
                                "/webjars/**",
                                "/images/**",
                                "/users/**",
                                "/resources/**",
                                "/static/**",
                                "/css/**",
                                "/js/**",
                                "*.js",
                                "*.css",
                                "*.jpg",
                                "*.jpeg",
                                "*.png"
                        ).permitAll()

                        .requestMatchers(
                                "/add_product",
                                "/save_product"
                        ).authenticated()

                        .requestMatchers(
                                "/all_users",
                                "/delete_user/*"
                        ).hasAuthority("admin")

                        .anyRequest().authenticated()
                )

                .formLogin((form) -> form
                        .loginPage("/")
                        .defaultSuccessUrl("/", true)
                        .loginProcessingUrl("/login")
                        .failureUrl("/?error")
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect(request.getContextPath());
                        })
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/?logout")
                );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


}
