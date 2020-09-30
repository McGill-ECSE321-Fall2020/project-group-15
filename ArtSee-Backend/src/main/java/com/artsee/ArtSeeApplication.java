package com.artsee;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class ArtSeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtSeeApplication.class, args);
	}

	public String greetings() {
		return "Hello World!";
	}

}
