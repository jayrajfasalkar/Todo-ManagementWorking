package com.todo_management_working.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorizeRequests) -> {
//                    authorizeRequests.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER");
//                    authorizeRequests.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN","USER");
//                    authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                    authorizeRequests.requestMatchers("/api/auth/**").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails jayraj = User.builder().username("jayraj").password(passwordEncoder().encode("jayraj@123")).roles("USER").build();
//
//        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin@123")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(jayraj, admin);
//    }
}
