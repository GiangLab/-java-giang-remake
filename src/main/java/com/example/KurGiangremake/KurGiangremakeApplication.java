package com.example.KurGiangremake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  // Enable Spring Cache for the entire application
public class KurGiangremakeApplication {
	public static void main(String[] args) {
		SpringApplication.run(KurGiangremakeApplication.class, args);
	}
}
