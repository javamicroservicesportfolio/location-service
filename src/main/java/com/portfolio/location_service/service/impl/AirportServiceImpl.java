package com.portfolio.location_service.service.impl;

import com.portfolio.location_service.mapper.AirportMapper;
import com.portfolio.location_service.model.Airport;
import com.portfolio.location_service.model.City;
import com.portfolio.location_service.repository.AirportRepository;
import com.portfolio.location_service.repository.CityRepository;
import com.portfolio.location_service.service.AirportService;
import com.portfolio.payload.request.AirportRequest;
import com.portfolio.payload.response.AirportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    @Override
    public AirportResponse createAirport(AirportRequest request) throws Exception {
        if (airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new Exception("Airport with IATA code " + request.getIataCode() + " already exists");
        }

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new Exception("City with ID " + request.getCityId() + " not found"));

        Airport airport = AirportMapper.toAirport(request);
        airport.setCity(city);
        return AirportMapper.toAirportResponse(airportRepository.save(airport));
    }

    @Override
    public AirportResponse getAirportById(Long id) throws Exception {
        return airportRepository.findById(id)
                .map(AirportMapper::toAirportResponse)
                .orElseThrow(() -> new Exception("Airport with ID " + id + " not found"));
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(AirportMapper::toAirportResponse)
                .toList();
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest request) throws Exception {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new Exception("Airport with ID " + id + " not found"));

        if (!airport.getIataCode().equals(request.getIataCode()) &&
                airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new Exception("Airport with IATA code " + request.getIataCode() + " already exists");
        }

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new Exception("City with ID " + request.getCityId() + " not found"));

        airport.setIataCode(request.getIataCode());
        airport.setName(request.getName());
        airport.setTimeZone(request.getTimeZone().toString());
        airport.setAddress(request.getAddress());
        airport.setGeoCode(request.getGeoCode());
        airport.setCity(city);

        return AirportMapper.toAirportResponse(airportRepository.save(airport));
    }

    @Override
    public void deleteAirport(Long id) throws Exception {
        if (!airportRepository.existsById(id)) {
            throw new Exception("Airport with ID " + id + " not found");
        }
        airportRepository.deleteById(id);
    }

    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {
        return airportRepository.findByCityId(cityId).stream()
                .map(AirportMapper::toAirportResponse)
                .collect(Collectors.toList());
    }
}
