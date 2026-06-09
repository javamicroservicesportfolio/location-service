package com.portfolio.location_service.controller;

import com.portfolio.location_service.service.CityService;
import com.portfolio.payload.request.CityRequest;
import com.portfolio.payload.response.ApiResponse;
import com.portfolio.payload.response.CityResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CityController {
    private final CityService cityService;

    /**
     * Endpoint to create a new city.
     * @param request {@link CityRequest} request the city request
     * @return {@link CityResponse} the created city response
     * @throws Exception if a city with the same city code already exists
     */
    @PostMapping
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest request) throws Exception {
        CityResponse response = cityService.createCity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint to get a city by its ID.
     * @param id the ID of the city to retrieve
     * @return {@link CityResponse} the city response
     * @throws Exception if the city with the specified ID does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id) throws Exception {
        CityResponse response = cityService.getCityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint to get all cities with pagination and sorting.
     * @param page the page number (default is 0)
     * @param size the page size (default is 10)
     * @param sortBy the field to sort by (default is "name")
     * @param sortDirection the sort direction, either "asc" or "desc" (default is "asc")
     * @return a page of {@link CityResponse} objects
     */
    @GetMapping
    public ResponseEntity<Page<CityResponse>> getAllCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CityResponse> response = cityService.getAllCities(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint to update an existing city by its ID.
     * @param id the ID of the city to update
     * @param request the updated city data
     * @return {@link CityResponse} the updated city response
     * @throws Exception if the city with the specified ID does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable Long id, @Valid @RequestBody CityRequest request) throws Exception {
        CityResponse response = cityService.updateCity(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint to delete a city by its ID.
     * @param id the ID of the city to delete
     * @return an {@link ApiResponse} indicating the result of the deletion
     * @throws Exception if the city with the specified ID does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCity(@PathVariable Long id) throws Exception {
        cityService.deleteCityById(id);
        ApiResponse response =
                new ApiResponse("City with id " + id + " deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint to search for cities by a keyword with pagination and sorting.
     * @param keyword the keyword to search for in city attributes
     * @param page the page number (default is 0)
     * @param size the page size (default is 10)
     * @param sortBy the field to sort by (default is "name")
     * @param sortDirection the sort direction, either "asc" or "desc" (default is "asc")
     * @return a page of {@link CityResponse} objects matching the search criteria
     */
    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> searchCities(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CityResponse> response = cityService.searchCities(keyword, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint to get cities by country code with pagination and sorting.
     * @param countryCode the country code to filter cities by
     * @param page the page number (default is 0)
     * @param size the page size (default is 10)
     * @param sortBy the field to sort by (default is "name")
     * @param sortDirection the sort direction, either "asc" or "desc" (default is "asc")
     * @return a page of {@link CityResponse} objects for the specified country code
     */
    @GetMapping("/country/{countryCode}")
    public ResponseEntity<Page<CityResponse>> getCitiesByCountry(
            @PathVariable String countryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CityResponse> response = cityService.getCitiesByCountryCode(countryCode, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint to check if a city with the specified city code exists.
     * @param cityCode the city code to check for existence
     * @return true if a city with the specified city code exists, false otherwise
     */
    @GetMapping("/exists/{cityCode}")
    public ResponseEntity<Boolean> cityExists(@PathVariable String cityCode) {
        boolean exists = cityService.cityExists(cityCode);
        return ResponseEntity.status(HttpStatus.OK).body(exists);
    }
}
