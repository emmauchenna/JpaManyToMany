package com.emma.jpamanytomany.service;

import com.emma.jpamanytomany.domain.Role;
import com.emma.jpamanytomany.domain.User;
import com.emma.jpamanytomany.dto.ResponseMessage;
import com.emma.jpamanytomany.dto.RoleDto;
import com.emma.jpamanytomany.dto.UserDto;
import com.emma.jpamanytomany.repository.RoleRepository;
import com.emma.jpamanytomany.repository.UserRepository;
import org.hibernate.query.UnknownSqlResultSetMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveRole(User user) {

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");

        roleAdmin.getUsers().add(user);
        user.getRoles().add(roleAdmin);
        roleRepository.save(roleAdmin);
    }


    public ResponseMessage createRole(String name){
        Role role = new Role();
        role.setName(name);
        Role save = roleRepository.save(role);
        return new ResponseMessage("00", "successful", save);
    }

    public ResponseMessage getAllRoles() {
        List<Role> all = roleRepository.findAll();
        return new ResponseMessage("00", "successful", all);
    }


    public ResponseMessage addRole(RoleDto role)  {
        Role newRole = new Role();
        newRole.setName(role.getName());

        List<Role> roleList = new ArrayList<>();
        roleList.add(newRole);

        for(int i=0; i< role.getUsers().size(); i++){

           UserDto userDto = role.getUsers().get(i);

            String email = userDto.getEmail();
            //Validation

            Optional<User> byEmail = userRepository.findByEmail(email);

            if(byEmail.isPresent()){
                  return   new ResponseMessage ("01","User with email Id is already Present");
            }

            User newUser = new User(0L, userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword(), null);
            newUser.setRoles(roleList);
            User savedUser = userRepository.save(newUser);
            return new ResponseMessage("00","Successfully created Role", savedUser);
        }
        return new ResponseMessage("00","Successfully created Role", null);
    }

}