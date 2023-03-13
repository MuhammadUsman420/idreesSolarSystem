package com.solar.dto;

import com.solar.model.SolarForm;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class LocationDto {
    private Long id;
    private String longitude;
    private String latitude;
    private String element;
    private SolarForm solar;
}