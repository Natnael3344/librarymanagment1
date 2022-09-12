package com.example.librarymanagment.service;

import com.example.librarymanagment.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    @Autowired
    public List<Users> findAllUsers();

    Users findUsersById(Long id);

    public void createUsers(Users user);

    Users updateUsers(Users user);

    void deleteUsers(Long id);
}
