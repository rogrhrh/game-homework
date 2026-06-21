package com.ahn.game_homework.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 전역 설정.
 * 현재는 BCryptPasswordEncoder Bean만 등록. FilterChain(허용/차단 경로 설정)은 추후 추가 예정.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // BCrypt: 단방향 해시 암호화. 같은 입력도 매번 결과가 달라 역산 불가. 비교는 matches()로.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
