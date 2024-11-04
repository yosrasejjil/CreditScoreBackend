package com.yosra.jwt.backend.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.yosra.jwt.backend.dtos.FinancialDataDto;

@Service
public class FlaskPredictionService {

    private final String FLASK_API_URL = "http://localhost:5000/predict";

    public Double getPrediction(FinancialDataDto financialDataDto) {
        try {
            // Convert DTO to a map for JSON
            RestTemplate restTemplate = new RestTemplate();

            // Send a POST request to the Flask API and get the response
            ResponseEntity<Double> response = restTemplate.postForEntity(FLASK_API_URL, financialDataDto, Double.class);

            // Extract prediction result from the response
            return response.getBody(); // This would return the JSON response from Flask

        } catch (Exception e) {
            // Handle any exception that may occur while calling Flask API
            return 0.0 ;
        }
    }
}
