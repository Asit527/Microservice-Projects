package com.flipkart.pickupservice.controller;

import com.flipkart.pickupservice.document.PickupPoint;
import com.flipkart.pickupservice.dto.PickupPointRequest;
import com.flipkart.pickupservice.service.PickupPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pickup-points")
public class PickupPointController {

    @Autowired
    private PickupPointService pickupPointService;

    @PostMapping
    public PickupPoint add(@RequestBody PickupPointRequest request) {
        return pickupPointService.addPickupPoint(request);
    }

    @GetMapping
    public List<PickupPoint> all() {
        return pickupPointService.getAll();
    }

    @GetMapping("/nearby")
    public GeoResults<PickupPoint> nearby(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "5") double radiusKm) {
        return pickupPointService.findNearby(lat, lng, radiusKm);
    }
}