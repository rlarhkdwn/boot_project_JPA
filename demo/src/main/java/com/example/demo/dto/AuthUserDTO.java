package com.example.demo.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class AuthUserDTO {
    private long id;
    private String email;
    private String role;
}
