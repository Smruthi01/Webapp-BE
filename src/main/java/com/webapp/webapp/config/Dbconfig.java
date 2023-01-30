package com.webapp.webapp.config;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;



@Configuration
public class Dbconfig {
    
    @Value("${spring.datasource.url}")
    private String url;

    @Autowired
    private SecretManager secretManager;

    @Bean

    public DataSource dataSource() {



        JsonNode secret = secretManager.getSecret();

        if (secret == null)

            return null;

        return DataSourceBuilder.create()
                // .driverClassName(driver)
                .url(url)
                .username(secret.get("username").asText())
                .password(secret.get("password").asText())
                .build();

    }
}
