package com.emma.jpamanytomany.controller;

import com.emma.jpamanytomany.domain.Role;
import com.emma.jpamanytomany.domain.User;
import com.emma.jpamanytomany.dto.ResponseMessage;
import com.emma.jpamanytomany.dto.RoleDto;
import com.emma.jpamanytomany.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping("/saveUserRole")
    public ResponseEntity saveRole2(@RequestBody User user){
        roleService.saveRole(user);
       return new ResponseEntity(null, HttpStatus.CREATED);
    }


    @PostMapping("/saveRole")
    public ResponseEntity<ResponseMessage> saveRole(@RequestParam String name){
        ResponseMessage role1 = roleService.createRole(name);
        return new ResponseEntity(role1, HttpStatus.CREATED);
    }

    @PostMapping("/addRole")
    public ResponseEntity<ResponseMessage> addRole(@RequestBody RoleDto newRole){
        ResponseMessage role1 = roleService.addRole(newRole);
        return new ResponseEntity(role1, HttpStatus.CREATED);
    }


    @GetMapping("/get-all")
    public ResponseMessage getAllRoles(){

        return roleService.getAllRoles();
    }
}
