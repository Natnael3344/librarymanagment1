package com.example.librarymanagment.entity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
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
@Table(name = "permission_role")
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Permission_Role {

//    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @Column(name = "permission_id",nullable = true)
//    @JsonIgnore
    private int permission_id;

//    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @Column(name = "role_id",nullable = true)
//    @JsonIgnore
    private int role_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Permission_Role(Long id,int permission_id, int role_id) {
        this.role_id = role_id;
        this.permission_id=permission_id;
        this.id=id;
    }


    public Permission_Role() {

    }
}
