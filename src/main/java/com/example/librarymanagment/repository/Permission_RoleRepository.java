package com.example.librarymanagment.repository;

import com.example.librarymanagment.entity.Permission_Role;
import com.example.librarymanagment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Permission_RoleRepository extends JpaRepository<Permission_Role, Long> {
}
