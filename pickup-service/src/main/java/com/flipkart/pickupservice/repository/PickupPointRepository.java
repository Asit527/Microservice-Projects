package com.flipkart.pickupservice.repository;

import com.flipkart.pickupservice.document.PickupPoint;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PickupPointRepository extends MongoRepository<PickupPoint, String> {

    GeoResults<PickupPoint> findByLocationNear(Point point, Distance distance);
