package com.emma.jpamanytomany.controller;

import com.emma.jpamanytomany.domain.User;
import com.emma.jpamanytomany.dto.ResponseMessage;
import com.emma.jpamanytomany.dto.UserDto;
import com.emma.jpamanytomany.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private  UserServiceImpl userService;

    @PostMapping("/addUser")
   // @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public User createUser(@RequestBody UserDto dto){
        return userService.createUser(dto);
    }
   //Http://localhost:9090/api/user/addRoles/{userid}
    @PostMapping("/addRoles/{userId}")
    public ResponseMessage addRolesToUser(@RequestBody List<Long> roleIds, @PathVariable Long userId){
        return userService.addRolesToUser(roleIds, userId);

    }

    @GetMapping("/get-all")
    public ResponseMessage getAll(){
        ResponseMessage  responseMessage =  userService.getAllUser();
        return responseMessage;
    }


    @GetMapping("/get-by/{userId}")
    public ResponseMessage getAll(@PathVariable Long userId){
        ResponseMessage  responseMessage =  userService.getUserById(userId);
        return responseMessage;
    }
}
