package com.artsee.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ArtSeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtSeeApplication.class, args);
	}

	@GetMapping("/")
	public String homepage() {
		return "Hello! This is the Artsee application!";
	}

}
