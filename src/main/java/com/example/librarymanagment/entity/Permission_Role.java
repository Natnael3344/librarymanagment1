package com.example.librarymanagment.entity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
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
@Table(name = "permission_role")
@Entity
public class Permission_Role {
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "permission_id",nullable = true)
    private Permissions permissions;
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id",nullable = true)
    private Roles roles;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public Permission_Role(Permissions permissions, Roles roles) {
        this.permissions = permissions;
        this.roles=roles;
    }


}
