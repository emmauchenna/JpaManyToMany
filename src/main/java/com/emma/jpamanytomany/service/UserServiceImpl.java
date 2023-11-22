package com.emma.jpamanytomany.service;

import com.emma.jpamanytomany.domain.Role;
import com.emma.jpamanytomany.domain.User;
import com.emma.jpamanytomany.dto.ResponseMessage;
import com.emma.jpamanytomany.dto.UserDto;
import com.emma.jpamanytomany.repository.RoleRepository;
import com.emma.jpamanytomany.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    private final  UserRepository repository;
    private final RoleRepository roleRepository;
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

     public User createUser(UserDto userDto){
            User user = new User(0, userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword(), null);
       return   repository.save(user);
    }

    public ResponseMessage addRolesToUser(List<Long> roleIds, Long userId) {
        //Check if UserId exist or not
        Optional<User> byId = repository.findById(userId);
        if(!byId.isPresent())
           return new ResponseMessage("01", "User id does not exist");
        User user = byId.get();

        List<Role> allById = roleRepository.findAllById(roleIds);
        //Check if Role id  exist or not
        if(allById == null){
            return new ResponseMessage("01", "Roles Ids does not exist");
        }
        user.setRoles(allById);
        User save = repository.save(user);
        return new ResponseMessage("00", "Successful", save);
    }


    public ResponseMessage addRolesToUser2(List<Role> roles, User user) {
        //Check if UserId exist or not
        Optional<User> byId = repository.findById(user.getId());
        if(!byId.isPresent())
            return new ResponseMessage("01", "User id does not exist");
        User user2 = byId.get();
        List<Long> roleIds = roles.stream().map(x -> x.getId()).collect(Collectors.toList());

        List<Role> roleList = roleRepository.findAllById(roleIds);
        //Check if Role id  exist or not
        if(roleList == null){
            return new ResponseMessage("01", "Roles Ids does not exist");
        }
        user.setRoles(roleList);
        User save = repository.save(user);
        return new ResponseMessage("00", "Successful", save);
    }




    public ResponseMessage getAllUser() {
        List<User> all = repository.findAll();
        return new ResponseMessage("00", "Success",all);
    }

    public ResponseMessage getUserById(Long userId) {
        Optional<User> byId = repository.findById(userId);
        if(byId.isPresent())
        return new ResponseMessage("00", "Success",byId.get());
        else
        return new ResponseMessage("01", "it does not exist");
    }
}
