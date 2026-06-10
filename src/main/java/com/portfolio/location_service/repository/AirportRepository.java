package com.portfolio.location_service.repository;

import com.portfolio.location_service.model.Airport;
import com.portfolio.payload.response.AirportResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByIataCode(String iataCode);
    List<Airport> findByCityId(Long cityId);

}
