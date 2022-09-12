package com.example.librarymanagment.service;

import com.example.librarymanagment.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface RolesService {
    @Autowired
    public List<Roles> findAllRoles();

    public Roles findRolesById(Long id);

    public void createRoles(Roles role);

    Roles updateRoles(Roles role);

    public void deleteRoles(Long id);
}
