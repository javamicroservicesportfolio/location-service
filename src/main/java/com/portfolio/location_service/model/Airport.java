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

    @Column(unique=true, nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private GeoCode  geoCode;

    @Column(name = "time_zone_id", length = 50)
    private String timeZone;

    @ManyToOne
    @JsonIgnore
    private City city;

    @JsonIgnore
    @Transient
    public String getDetailedName(){
        if (city != null && city.getCountryCode() != null) {
            return this.name.toUpperCase() + "/" + city.getCityCode();
        }

        return this.name.toUpperCase();
    }

}
