package com.example.librarymanagment.service.services;
import com.example.librarymanagment.entity.Permissions;
import com.example.librarymanagment.exceptions.NotFoundException;
import com.example.librarymanagment.repository.PermissionsRepository;
import com.example.librarymanagment.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Primary
public  class PermissionsServices implements PermissionsService {
    @Autowired
    private  PermissionsRepository permissionsRepository;


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Permissions> findAllPermissions() {
        return permissionsRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Permissions findPermissionsById(Long id) {
        return permissionsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Permission not found with ID %d", id)));
    }

    @Override
    public void createPermissions(Permissions permission) {
        permissionsRepository.save(permission);
    }

    @Override
    public Permissions updatePermissions(Permissions permission) {
        boolean exist = permissionsRepository.existsById(permission.getId());
        if(exist){
            return permissionsRepository.save(permission);
        }
        return null;
    }



    @Override
    public void deletePermissions(Long id) {
        permissionsRepository.deleteById(id);
    }

}

