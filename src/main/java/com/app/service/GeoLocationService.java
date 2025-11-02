package com.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoLocationService {

    private static final String API_KEY = "";
    private static final String URL = "";

    public String getLocation(String ipAdress){
        RestTemplate restTemplate = new RestTemplate();
        String url = URL + ipAdress + "?access_key" + API_KEY;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
