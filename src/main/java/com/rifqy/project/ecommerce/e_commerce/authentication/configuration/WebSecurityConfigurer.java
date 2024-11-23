package com.rifqy.project.ecommerce.e_commerce.authentication.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.rifqy.project.ecommerce.e_commerce.applicationuser.ApplicationUserService;
import com.rifqy.project.ecommerce.e_commerce.authentication.jwt.JwtAuthenticationEntryPoint;
import com.rifqy.project.ecommerce.e_commerce.authentication.jwt.JwtAuthenticationTokenFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigurer {
        private final ApplicationUserService applicationUserService;
        private final JwtAuthenticationEntryPoint unauthorizedHandler;

        @Bean
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
                return new JwtAuthenticationTokenFilter();
        }

        @Bean
        BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder(12);
        }

        @Bean
        AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
                AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
                                .getSharedObject(AuthenticationManagerBuilder.class);
                authenticationManagerBuilder.userDetailsService(this.applicationUserService)
                                .passwordEncoder(this.bCryptPasswordEncoder());

                return authenticationManagerBuilder.build();
        }

        @Bean
        CorsConfigurationSource configurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedHeaders(List.of("Authorization", "Cahce-Control", "Contetnt-Type"));
                configuration.setAllowedOriginPatterns(List.of("http://localhost:5173/"));
                configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                configuration.setAllowCredentials(true);
                configuration.setExposedHeaders(List.of("Authorization"));

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);

                return source;
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                                .cors(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable())
                                .exceptionHandling(
                                                exceptionHandling -> exceptionHandling
                                                                .authenticationEntryPoint(unauthorizedHandler))
                                .sessionManagement(
                                                sessionManagement -> sessionManagement
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(
                                                request -> request
                                                                .requestMatchers("/tokens", "/greetings",
                                                                                "/swagger-ui/**", "/v3/api-docs/**",
                                                                                "/error")
                                                                .permitAll()
                                                                .requestMatchers(HttpMethod.POST, "/registerations",
                                                                                "/registerations/**")
                                                                .permitAll()
                                                                .requestMatchers(HttpMethod.GET, "/items", "/items/**")
                                                                .permitAll()
                                                                .anyRequest()
                                                                .authenticated());

                httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(),
                                UsernamePasswordAuthenticationFilter.class);

                return httpSecurity.build();
        }
}