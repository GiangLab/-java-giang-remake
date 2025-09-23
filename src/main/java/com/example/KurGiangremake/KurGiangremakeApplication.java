package com.example.KurGiangremake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching      // Enable Spring Cache (Redis)
@EnableScheduling   //Enable scheduling to run recurring jobs
@EnableAsync        // Enable asynchronous processing
public class KurGiangremakeApplication {
	public static void main(String[] args) {
		SpringApplication.run(KurGiangremakeApplication.class, args);
	}
}
