package com.portfolio.location_service.repository;

import com.portfolio.location_service.model.Airport;
import com.portfolio.payload.response.AirportResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    AirportResponse findByIataCode(String iataCode);
    List<AirportResponse> findByCityId(Long cityId);

}
