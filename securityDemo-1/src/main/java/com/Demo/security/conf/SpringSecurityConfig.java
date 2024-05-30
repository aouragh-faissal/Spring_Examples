package com.Demo.security.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	

	@Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/admin").hasRole("ADMIN");
	        auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2/**")).hasRole("ADMIN");	
			auth.requestMatchers("/user").hasAnyRole("ADMIN", "USER");
			auth.anyRequest().authenticated();
		}).headers(headers -> headers.frameOptions((frame) -> frame.sameOrigin()))
				.csrf((csrf) -> csrf.ignoringRequestMatchers(
				AntPathRequestMatcher.antMatcher("/h2/**")))
	.formLogin(Customizer.withDefaults()).build();
	}
	
//	@Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((authorize) -> 
//                        authorize
//                                .requestMatchers("/h2/**").permitAll()
//                                .anyRequest().authenticated())
//                .csrf((csrf) -> csrf.disable())
//                .headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()))
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());
//        return http.build();
//    }
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, 
			BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = 
				http.getSharedObject(AuthenticationManagerBuilder.class);
	authenticationManagerBuilder.userDetailsService(
			customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}
	
	/*
	 * @Bean UserDetailsService users() { UserDetails user = User.builder()
	 * .username("user") .password(passwordEncoder().encode("user"))
	 * .roles("USER").build(); UserDetails admin = User.builder() .username("admin")
	 * .password(passwordEncoder().encode("admin")) .roles("USER", "ADMIN").build();
	 * return new InMemoryUserDetailsManager(user, admin); }
	 */
		
	@Bean
	 BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}