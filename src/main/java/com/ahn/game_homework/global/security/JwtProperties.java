package com.ahn.game_homework.global.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * application.yml의 "jwt:" 설정을 자동으로 바인딩하는 설정 클래스.
 * @ConfigurationProperties: yml 키와 필드명을 매핑해준다 (jwt.secret → secret 필드).
 */
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final String secret;
    private final TokenExpiration tokenExpiration;

    public JwtProperties(String secret, TokenExpiration tokenExpiration) {
        this.secret = secret;
        this.tokenExpiration = tokenExpiration;
    }

    @Getter
    public static class TokenExpiration {
        private static final long MILLIS_PER_SECOND = 1000L;
        private static final long MILLIS_PER_DAY    = 24 * 60 * 60 * MILLIS_PER_SECOND;
        private static final long SECONDS_PER_DAY   = 24 * 60 * 60L;

        private final long access;  // yml 단위: 초
        private final long refresh; // yml 단위: 일

        public TokenExpiration(long access, long refresh) {
            this.access = access;
            this.refresh = refresh;
        }

        public long getAccessInMillis()   { return access * MILLIS_PER_SECOND; } // JWT 생성 시 만료 계산용
        public long getRefreshInMillis()  { return refresh * MILLIS_PER_DAY; }
        public long getRefreshInSeconds() { return refresh * SECONDS_PER_DAY; }  // Redis TTL 설정용 (초 단위)
    }

}
