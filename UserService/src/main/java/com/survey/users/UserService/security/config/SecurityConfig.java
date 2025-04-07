package com.survey.users.UserService.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.survey.users.UserService.security.filter.ExceptionHandlingFilter;
import com.survey.users.UserService.security.filter.JWTAuthenticationFilter;
import com.survey.users.UserService.security.filter.JWTAuthorizationFilter;
import com.survey.users.UserService.security.filter.RestAuthenticationEntryPoint;
import com.survey.users.UserService.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.survey.users.UserService.security.config.SecurityConfig.HTTP.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    enum HTTP {
        GET,
        POST,
        PUT,
        DELETE
    }

    private final ObjectMapper objectMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));

        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/webjars/swagger-ui/**").permitAll()
                .requestMatchers("/target-user/**").permitAll()
                .requestMatchers(match(HTTP.POST, "/api/users", "/target-user/**")).permitAll()
                .requestMatchers(match(HTTP.POST, "/api/users", "/api/target-user/**")).permitAll()
                .requestMatchers(match(HTTP.GET, "/api/permissions")).hasAnyAuthority("can_update_users", "can_create_users")
                .requestMatchers(match(HTTP.POST, "/api/users/create")).hasAuthority("can_create_users")
                .requestMatchers(match(HTTP.PUT, "/api/users/*")).hasAuthority("can_update_users")
                .requestMatchers(match(HTTP.DELETE, "/api/users/*")).hasAuthority("can_delete_users")
                .requestMatchers(match(HTTP.GET, "/ws/**")).permitAll()
                .anyRequest().authenticated()
        );


        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
            httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403");
            httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(restAuthenticationEntryPoint);
        });

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Custom filters
        http
                .addFilterBefore(new ExceptionHandlingFilter(objectMapper), JWTAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(jwtUtils, authenticationManager, objectMapper), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new JWTAuthorizationFilter(jwtUtils), JWTAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:8080"));
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    private AntPathRequestMatcher[] match(String... patterns) {
        return Stream.of(patterns)
                .map(AntPathRequestMatcher::new)
                .toArray(AntPathRequestMatcher[]::new);
    }

    private AntPathRequestMatcher[] match(HTTP http, String... patterns) {
        return Stream.of(patterns)
                .map(pattern -> new AntPathRequestMatcher(pattern, http.name()))
                .toArray(AntPathRequestMatcher[]::new);
    }

}
