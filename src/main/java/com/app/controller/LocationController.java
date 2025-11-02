package com.app.controller;

import com.app.service.GeoLocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LocationController {

    private GeoLocationService geoLocationService;

    public LocationController(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;


    }
    @GetMapping("/get-location")
    public String getLocation(HttpServletRequest request){
        String clientIp = getClentIp(request);
        String locationInfo = geoLocationService.getLocation(clientIp);
        return locationInfo;

    }

    private String getClentIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String xForwardFor = request.getHeader("X-Forward-For");
        if (xForwardFor!= null && !xForwardFor.isEmpty()) {
            remoteAddr = xForwardFor.split(",")[0];
        }
        return remoteAddr;
    }
}
