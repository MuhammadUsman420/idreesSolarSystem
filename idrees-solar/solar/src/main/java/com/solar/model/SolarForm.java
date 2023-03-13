package com.solar.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "solar")
public class SolarForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @OneToMany(mappedBy = "solar",cascade = CascadeType.REMOVE)
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
