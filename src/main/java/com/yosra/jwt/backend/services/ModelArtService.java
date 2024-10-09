//package com.yosra.jwt.backend.services;
//
//import com.yosra.jwt.backend.dtos.FinancialDataDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class ModelArtService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private final String MODELARTS_ENDPOINT = "<Your_ModelArts_Endpoint>";  // Replace with actual endpoint URL
//
//    public String getModelScore(FinancialDataDto financialDataDto) {
//        // Prepare the request body based on your ModelArts model's input requirements
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("financial_data", financialDataDto);  // Adjust the key and structure per ModelArts requirements
//
//        // Make the POST request to ModelArts and get the response
//        ResponseEntity<String> response = restTemplate.postForEntity(MODELARTS_ENDPOINT, requestBody, String.class);
//
//        // Return the score or result from the ModelArts endpoint
//        return response.getBody();
//    }
//}
