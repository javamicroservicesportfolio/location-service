package com.portfolio.location_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portfolio.embeddable.Address;
import com.portfolio.embeddable.GeoCode;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true, nullable = false, length = 3)
    private String iataCode;

    @Embedded
    private Address address;

    @Embedded
    private GeoCode  geoCode;

    @ManyToOne
    @JsonIgnore
    private City city;
}
