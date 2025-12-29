package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class UserDTO {
    private String email;
    private String pwd;
    private String nickName;
    private LocalDateTime lastLogin;
    private LocalDateTime regDate, modDate;
    private List<AuthUserDTO> authList;
}
