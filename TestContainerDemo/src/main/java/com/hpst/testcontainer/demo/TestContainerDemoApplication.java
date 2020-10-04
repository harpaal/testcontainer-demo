package com.hpst.testcontainer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TestContainerDemoApplication implements CommandLineRunner {

	@Autowired
	UserJpaRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(TestContainerDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User savedUser = userRepo.save(new User(1l, "Harpal"));
		log.info("savedUser"+savedUser.getName());
	}
	
	

}
