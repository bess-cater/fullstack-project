package com.packt.mybase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.packt.mybase.jwtutils.JWTFilter;
import com.packt.mybase.jwtutils.JWTUtil;
import com.packt.mybase.jwtutils.LoginFilter;

import org.springframework.security.web.SecurityFilterChain;

// import com.packt.mybase.service.UserDetailsServiceImp;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.authenticationConfiguration = authenticationConfiguration;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((auth) -> auth.disable());

				//From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

				//http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

				//경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll()
												.requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

				//세션 설정
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User.builder()
    //         .username("user")
    //         .password(passwordEncoder().encode("password"))
    //         .roles("USER")
    //         .build();

    //     return new InMemoryUserDetailsManager(user);
    // }
    // @Autowired
	// private UserDetailsServiceImp userDetailsService;
    
    // @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth)
	// 		throws Exception  {
	// 	auth.userDetailsService(userDetailsService)
	// 	.passwordEncoder(new BCryptPasswordEncoder());
	// }

    @Bean
    public BCryptPasswordEncoder bcEncoder() {
        return new BCryptPasswordEncoder();
         
    }

    // @Bean
    // public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    //     AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
    //     auth.userDetailsService(userDetailsService)
    //         .passwordEncoder(passwordEncoder());
    //     return auth.build();
    // }

}
