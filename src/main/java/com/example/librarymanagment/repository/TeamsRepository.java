package com.example.librarymanagment.repository;

import com.example.librarymanagment.entity.Teams;
import com.example.librarymanagment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends JpaRepository<Teams,Long> {
}