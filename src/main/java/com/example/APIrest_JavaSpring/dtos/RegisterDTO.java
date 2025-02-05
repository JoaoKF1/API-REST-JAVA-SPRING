package com.example.APIrest_JavaSpring.dtos;

import com.example.APIrest_JavaSpring.domain.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
