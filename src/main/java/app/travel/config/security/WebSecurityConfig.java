package app.travel.config.security;

import app.travel.config.middleware.filter.MiddlewareFilterConfig;
import app.travel.value.AppCoreValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebSecurityConfig {

    AppCoreValue appCoreValue;

    AuthenticationEntryPoint authenticationEntryPoint;

    AccessDeniedHandler accessDeniedHandler;

    MiddlewareFilterConfig middlewareFilterConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        String prefixPath = String.format("%s/**", appCoreValue.getPrefixPath());

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(this::corsConfigurationMethod)
                .exceptionHandling(exHandling -> exHandling
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .addFilterBefore(middlewareFilterConfig, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorize ->
                                authorize.requestMatchers("/auth/**").permitAll()
                                        .requestMatchers(
                                                "/v3/api-docs/**",
                                                "/swagger-ui/**",
                                                "/swagger-ui.html"
                                        ).permitAll()
                                        .anyRequest().authenticated()
                )
                .build();
    }

    private void corsConfigurationMethod(CorsConfigurer<HttpSecurity> corsConfigurer){

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(Boolean.TRUE);
        corsConfiguration.setMaxAge(600L);

        UrlBasedCorsConfigurationSource urlBasedCors = new UrlBasedCorsConfigurationSource();

        String patternUrl = String.format("%s/**", appCoreValue.getPrefixPath());
        urlBasedCors.registerCorsConfiguration(patternUrl, corsConfiguration);

        corsConfigurer.configurationSource(urlBasedCors);

    }

}
