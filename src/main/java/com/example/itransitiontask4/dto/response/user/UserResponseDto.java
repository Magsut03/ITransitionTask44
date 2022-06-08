package com.example.itransitiontask4.dto.response.user;

import com.example.itransitiontask4.model.enums.UserStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String name;
    private String email;
    private UserStateEnum states;
    private LocalDateTime lastLoginTime;
    private LocalDateTime registrationTime;
}
