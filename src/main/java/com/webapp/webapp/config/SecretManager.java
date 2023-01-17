package com.webapp.webapp.config;

import org.springframework.stereotype.Component;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;



@Component
public class SecretManager {

    @Value("${secretsmanager.secretname}")
    private String secretName;

    @Value("${secretsmanager.endpoint}")
    private String endpoint;

    @Value("${secretsmanager.region}")
    private String region;

    public JsonNode getSecret() {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode secretsJson = null;
        GetSecretValueResult getSecretValueResponse = null;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
        AWSSecretsManager clientBuilder = AWSSecretsManagerClientBuilder.standard()
                                                                        .withCredentials(new DefaultAWSCredentialsProviderChain())
                                                                        .withRegion(Regions.US_EAST_1).build();

        getSecretValueResponse = clientBuilder.getSecretValue(getSecretValueRequest);

        String secret = getSecretValueResponse.getSecretString();

        if (secret != null) {

            try {

                secretsJson = objectMapper.readTree(secret);

                return secretsJson;

            } catch (JsonProcessingException e) {

                e.printStackTrace();

            }

        }

        return null;

    }



}