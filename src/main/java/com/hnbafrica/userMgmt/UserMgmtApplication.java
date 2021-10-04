package com.hnbafrica.userMgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class UserMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMgmtApplication.class, args);
	}

}
