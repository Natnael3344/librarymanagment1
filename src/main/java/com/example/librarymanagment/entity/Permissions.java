package com.example.librarymanagment.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permissions")

public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "title")
    private String title;
    @JsonManagedReference
    @OneToMany(mappedBy = "permissions")
    private Set<Permission_Role> permission_role;


    public Permissions(Long id, LocalDateTime created_at, LocalDateTime updated_at, String title,LocalDateTime deleted_at) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.title = title;
        this.deleted_at=deleted_at;

    }


}
