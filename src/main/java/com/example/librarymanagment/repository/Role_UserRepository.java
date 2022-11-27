package com.example.librarymanagment.repository;

import com.example.librarymanagment.entity.Role_User;
import com.example.librarymanagment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Role_UserRepository extends JpaRepository<Role_User, Long> {
}
