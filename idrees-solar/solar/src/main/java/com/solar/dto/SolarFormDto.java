package com.solar.dto;


import com.solar.model.Location;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class SolarFormDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String country;

    private String email;
    private String phoneNumber;
    private String consumption;
    private String notes;
    private Boolean privacyCheck;

    private List<Location> locations;
    private String roofType;
    private String roofInclination;
    private String roofing;
    private String buildingHeight;

    private String area;
    private Boolean leaseRooftop;
    private Boolean rentRooftop;
    private Boolean buyRooftop;
    private String attachment;
}
