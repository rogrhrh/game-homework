package com.ahn.game_homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GameHomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameHomeworkApplication.class, args);
	}

}
