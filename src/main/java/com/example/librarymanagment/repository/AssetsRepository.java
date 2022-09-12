package com.example.librarymanagment.repository;
import com.example.librarymanagment.entity.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsRepository extends JpaRepository<Assets, Long> {
}
