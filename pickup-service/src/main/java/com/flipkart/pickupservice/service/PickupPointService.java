package com.flipkart.pickupservice.service;

import com.flipkart.pickupservice.document.PickupPoint;
import com.flipkart.pickupservice.dto.PickupPointRequest;
import com.flipkart.pickupservice.repository.PickupPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickupPointService {

    @Autowired
    private PickupPointRepository pickupPointRepository;

    public PickupPoint addPickupPoint(PickupPointRequest request) {
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setName(request.getName());
        pickupPoint.setAddress(request.getAddress());
        pickupPoint.setLocation(new GeoJsonPoint(request.getLng(), request.getLat()));
        return pickupPointRepository.save(pickupPoint);
    }

    public List<PickupPoint> getAll() {
        return pickupPointRepository.findAll();
    }

    public GeoResults<PickupPoint> findNearby(double lat, double lng, double radiusKm) {
        Point point = new Point(lng, lat);
        Distance distance = new Distance(radiusKm, Metrics.KILOMETERS);
        return pickupPointRepository.findByLocationNear(point, distance);
    }
}