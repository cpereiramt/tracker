package com.claytonpereira.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.claytonpereira"})
@EnableJpaRepositories({"com.claytonpereira"})
@EntityScan({"com.claytonpereira"})
public class TrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TrackerApplication.class, args);
	}

}
