package com.example.librarymanagment.service.services;

import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.entity.Roles;
import com.example.librarymanagment.entity.Teams;
import com.example.librarymanagment.exceptions.NotFoundException;
import com.example.librarymanagment.repository.RolesRepository;
import com.example.librarymanagment.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Primary
public  class RolesServices implements RolesService {
    @Autowired
    private  RolesRepository rolesRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Roles> findAllRoles() {
        return rolesRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Roles findRolesById(Long id) {
        return rolesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Role not found with ID %d", id)));
    }

    @Override
    public void createRoles(Roles role) {
        rolesRepository.save(role);
    }

    @Override
    public Roles updateRoles(Roles role) {
        boolean exist = rolesRepository.existsById(role.getId());
        if(exist){
            return rolesRepository.save(role);
        }
        return null;
    }



    @Override
    public void deleteRoles(Long id) {
        rolesRepository.deleteById(id);
    }

}

