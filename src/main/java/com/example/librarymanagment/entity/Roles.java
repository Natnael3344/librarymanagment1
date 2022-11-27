package com.example.librarymanagment.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "roles")
public class Roles implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;


    @Column(name = "created_at")
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


//    @OneToMany(mappedBy = "roles",fetch = FetchType.LAZY)
//    Set<Permission_Role> permission_role;


//    @OneToMany(mappedBy = "roles",fetch = FetchType.LAZY)
//    private Set<Role_User> role_user;

    public Roles(Long id, LocalDateTime created_at, LocalDateTime updated_at, String title,LocalDateTime deleted_at) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.title = title;
        this.deleted_at=deleted_at;
    }

    public Roles() {

    }
}
