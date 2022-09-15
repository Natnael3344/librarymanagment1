package com.example.librarymanagment.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "teams")
public class Teams {

    @Id
    private Long id;
    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @Column(name = "deleted_at")
    @UpdateTimestamp
    private LocalDateTime deleted_at;
    @Column(name = "name", nullable = false)
    private String name;


    @OneToMany(mappedBy = "teams",fetch = FetchType.LAZY)
    private Set<Users> users;


    @OneToMany(mappedBy = "teams",fetch = FetchType.LAZY)
    private Set<Assets> assets;


    @OneToMany(mappedBy = "teams",fetch = FetchType.LAZY)
    private Set<Transactions> transactions;
    public Teams(Long id, LocalDateTime created_at, LocalDateTime updated_at, String name,LocalDateTime deleted_at) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.deleted_at=deleted_at;
    }

    public Teams() {

    }
}
