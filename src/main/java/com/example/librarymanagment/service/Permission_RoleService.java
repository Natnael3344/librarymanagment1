package com.example.librarymanagment.service;

import com.example.librarymanagment.entity.Permission_Role;

import java.util.List;
import java.util.Optional;

public interface Permission_RoleService {
    public List<Permission_Role> findAllPermission_Role();

    public Optional<Permission_Role> findPermission_RoleById(Long id);

    public void createPermission_Role(Permission_Role permission_roles);

    public void updatePermission_Role(Permission_Role permission_roles);

    public void deletePermission_Role(Long id);
}
