package com.ahn.game_homework;

import com.ahn.game_homework.global.security.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing: BaseEntity의 @CreatedDate/@LastModifiedDate 자동 주입을 활성화한다.
// @EnableConfigurationProperties: @ConfigurationProperties 클래스를 Bean으로 등록한다.
@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(JwtProperties.class)
public class GameHomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameHomeworkApplication.class, args);
	}

}
