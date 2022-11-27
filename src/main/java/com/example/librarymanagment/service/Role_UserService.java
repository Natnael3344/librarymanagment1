package com.example.librarymanagment.service;

import com.example.librarymanagment.entity.Role_User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface Role_UserService {
    @Autowired
    public List<Role_User> findAllRole_User();

     Role_User findRole_UserById(Long id);

    public void createRole_User(Role_User role_users);

    Role_User updateRole_User(Role_User role_users);

     void deleteRole_User(Long id);
}
