package com.metacoding.authblog._core.config;

import com.metacoding.authblog.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 평문을 hash로 바꿔준다
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable());

        http.authorizeHttpRequests(r -> {
                r.requestMatchers("/s/**")
                        .authenticated()
                        .anyRequest()
                        .permitAll();
        }).formLogin(f -> f.loginPage("/login-form")
                .loginProcessingUrl("/login")
                //.defaultSuccessUrl("/")
                .successHandler((request, response, authentication) -> {
            User user = (User) authentication.getPrincipal();
                    System.out.println("세션 저장 성공");
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser", user);
            response.sendRedirect("/");
        }));

        return http.build();
    }
}
