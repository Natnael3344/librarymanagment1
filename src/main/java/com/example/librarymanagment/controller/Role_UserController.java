package com.example.librarymanagment.controller;

import com.example.librarymanagment.entity.Role_User;
import com.example.librarymanagment.entity.Roles;
import com.example.librarymanagment.repository.Role_UserRepository;
import com.example.librarymanagment.repository.RolesRepository;
import com.example.librarymanagment.service.Role_UserService;
import com.example.librarymanagment.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT, RequestMethod.DELETE,}
)
@Configuration
public class Role_UserController {
    @Autowired
    private Role_UserService role_userService;
    @Autowired
    private Role_UserRepository role_userRepository;
    @GetMapping("/viewRolesUser")
    public List<Role_User> findAllRole_User(){
        return role_userRepository.findAll();
    }
}
