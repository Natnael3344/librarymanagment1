package com.example.librarymanagment.entity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "role_user")
@Entity
public class Role_User {

    @Id
    private Long id;

    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = true)
    private Users users;
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id",nullable = true)
    private Roles roles;

    public Role_User(Users users, Roles roles) {
        this.users = users;
        this.roles = roles;
    }
}
