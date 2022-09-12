package com.example.librarymanagment.service;

import com.example.librarymanagment.entity.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PermissionsService {
    @Autowired
    public List<Permissions> findAllPermissions();

    public Permissions findPermissionsById(Long id);

    public void createPermissions(Permissions permission);

    Permissions updatePermissions(Permissions permission);

    public void deletePermissions(Long id);
}
