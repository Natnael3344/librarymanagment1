package com.example.librarymanagment.repository;

import com.example.librarymanagment.entity.Permissions;
import com.example.librarymanagment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
}