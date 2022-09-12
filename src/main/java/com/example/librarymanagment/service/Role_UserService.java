package com.example.librarymanagment.service;

import com.example.librarymanagment.entity.Role_User;

import java.util.List;
import java.util.Optional;

public interface Role_UserService {
    public List<Role_User> findAllRole_User();

    public Optional<Role_User> findRole_UserById(Long id);

    public void createRole_User(Role_User role_users);

    public void updateRole_User(Role_User role_users);

    public void deleteRole_User(Long id);
}
