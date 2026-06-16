package com.ahn.game_homework.global.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
    public static class TokenExpiration{
        private static final long MILLIS_PER_SECOND = 1000L;
        private static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
        private static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
        private static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
        private static final long SECONDS_PER_DAY = 24 * 60 * 60L;

        private final long access;
        private final long refresh;

        public TokenExpiration(long access, long refresh){
            this.access = access;
            this.refresh = refresh;
        }

        public long getAccessInMillis() { return access * MILLIS_PER_SECOND; }
        public long getRefreshInMillis() { return refresh * MILLIS_PER_DAY; }
        public long getRefreshInSeconds() { return refresh * SECONDS_PER_DAY; }
    }

}
