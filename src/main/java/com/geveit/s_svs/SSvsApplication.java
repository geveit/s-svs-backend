package com.geveit.s_svs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SSvsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SSvsApplication.class, args);
	}

}
