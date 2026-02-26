package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class databaseconfig {

    @PostConstruct
    public void fixDatabaseUrl() {
        String url = System.getenv("DATABASE_URL");

        if (url != null && url.startsWith("postgres://")) {
            url = url.replace("postgres://", "jdbc:postgresql://");
            System.setProperty("spring.datasource.url", url);
        }

        System.out.println("✅ JDBC URL = " + url);
    }
}