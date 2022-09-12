package com.example.librarymanagment.service.services;

import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.entity.Users;
import com.example.librarymanagment.exceptions.NotFoundException;
import com.example.librarymanagment.repository.UsersRepository;
import com.example.librarymanagment.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public  class UsersServices implements UsersService{
    @Autowired
    private  UsersRepository usersRepository;


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Users findUsersById(Long id) {
        return usersRepository.findById(id)
               .orElseThrow(() -> new NotFoundException(String.format("Users not found with ID %d", id)));
    }

    @Override
    public void createUsers(Users user) {
        usersRepository.save(user);
    }

    @Override
    public Users updateUsers(Users user) {
        boolean exist = usersRepository.existsById(user.getId());
        if(exist){
            return usersRepository.save(user);
        }
        return null;
    }



    @Override
    public void deleteUsers(Long id) {
        usersRepository.deleteById(id);
    }
}
