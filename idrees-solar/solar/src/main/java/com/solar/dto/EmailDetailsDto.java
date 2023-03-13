package com.solar.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class EmailDetailsDto {
    private String recipient;
    private String subject;
    private SolarFormDto solarFormDto;

}
