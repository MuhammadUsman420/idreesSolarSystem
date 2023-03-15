package com.solar.dto;

import lombok.*;

import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class MessageDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String message;

}
