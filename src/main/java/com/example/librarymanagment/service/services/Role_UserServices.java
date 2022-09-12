package com.example.librarymanagment.service.services;

import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.entity.Role_User;
import com.example.librarymanagment.entity.Teams;
import com.example.librarymanagment.exceptions.NotFoundException;
import com.example.librarymanagment.repository.Role_UserRepository;
import com.example.librarymanagment.service.Role_UserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class Role_UserServices implements Role_UserService {
    private final Role_UserRepository role_userRepository;

    public Role_UserServices(Role_UserRepository role_userRepository) {
        this.role_userRepository = role_userRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Role_User> findAllRole_User() {
        return role_userRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<Role_User> findRole_UserById(Long id) {
        return role_userRepository.findById(id);
//                .orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
    }

    @Override
    public void createRole_User(Role_User role_user) {
        role_userRepository.save(role_user);
    }

    @Override
    public void updateRole_User(Role_User role_user) {
        role_userRepository.save(role_user);
    }



    @Override
    public void deleteRole_User(Long id) {
        final Role_User role_user = role_userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
        role_userRepository.deleteById(role_user.getId());
    }

}

