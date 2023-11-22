package com.emma.jpamanytomany.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RoleDto {
    public String name;
    public ArrayList<UserDto> users;
}
