package com.example.librarymanagment.service.services;

import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.entity.Permission_Role;
import com.example.librarymanagment.entity.Teams;
import com.example.librarymanagment.exceptions.NotFoundException;
import com.example.librarymanagment.repository.Permission_RoleRepository;
import com.example.librarymanagment.service.Permission_RoleService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class Permission_RoleServices implements Permission_RoleService {
    private final Permission_RoleRepository permission_roleRepository;

    public Permission_RoleServices(Permission_RoleRepository permission_roleRepository) {
        this.permission_roleRepository = permission_roleRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Permission_Role> findAllPermission_Role() {
        return permission_roleRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<Permission_Role> findPermission_RoleById(Long id) {
        return permission_roleRepository.findById(id);
//                .orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
    }

    @Override
    public void createPermission_Role(Permission_Role permission_role) {
        permission_roleRepository.save(permission_role);
    }

    @Override
    public void updatePermission_Role(Permission_Role permission_role) {
        permission_roleRepository.save(permission_role);
    }



    @Override
    public void deletePermission_Role(Long id) {
        final Permission_Role permission_role = permission_roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
        permission_roleRepository.deleteById(permission_role.getId());
    }

}

