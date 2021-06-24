package com.bridgelabz.onlinebookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class OnlineBookStoreBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreBackendApplication.class, args);
    }

}
