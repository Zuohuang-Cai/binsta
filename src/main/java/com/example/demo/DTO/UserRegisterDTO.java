package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserRegisterDTO {
    private String Username;
    private String Password;
    private List<String> Roles;
}
