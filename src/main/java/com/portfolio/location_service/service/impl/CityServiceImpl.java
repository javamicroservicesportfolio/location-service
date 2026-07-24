package com.portfolio.location_service.service.impl;

import com.portfolio.location_service.mapper.CityMapper;
import com.portfolio.location_service.model.City;
import com.portfolio.location_service.repository.CityRepository;
import com.portfolio.location_service.service.CityService;
import com.portfolio.payload.request.CityRequest;
import com.portfolio.payload.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public CityResponse createCity(CityRequest request) throws Exception {
        if (cityRepository.existsByCityCode(request.getCityCode())) {
            throw new Exception("City with code " + request.getCityCode() + " already exists");
        }

        City city = CityMapper.toCity(request);
        City result = cityRepository.save(city);

        return CityMapper.toResponse(result);
    }

    @Override
    public CityResponse getCityById(Long id) throws Exception {
        return cityRepository.findById(id)
                .map(CityMapper::toResponse)
                .orElseThrow(
                        () -> new Exception("City with id " + id + " not found")
                );
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) throws Exception {
        City city = cityRepository.findById(id)
                .orElseThrow(
                        () -> new Exception("City with id " + id + " not found")
                );

        if (!city.getCityCode().equals(request.getCityCode()) && cityRepository.existsByCityCodeAndIdNot(request.getCityCode(), id)) {
            throw new Exception("City with code " + request.getCityCode() + " already exists");
        }

        City updatedCity = CityMapper.updateCity(city, request);
        cityRepository.save(updatedCity);
        return CityMapper.toResponse(updatedCity);
    }

    @Override
    public void deleteCityById(Long id) throws Exception {
        City city = cityRepository.findById(id)
                .orElseThrow(
                        () -> new Exception("City with id " + id + " not found")
                );

        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository.searchByKeyword(keyword, pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }
}
