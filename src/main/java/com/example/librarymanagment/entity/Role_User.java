package com.example.librarymanagment.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Table(name = "role_user")
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Role_User implements  Serializable{
    @Id
    @GeneratedValue
    private Long id;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="user_id")
//    private Users user_id;
//
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="role_id")
//    private Roles role_id;

//    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @Column(name = "user_id",nullable = false)
////    @JsonIgnore
    private Long user_id;
//
////    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @Column(name = "role_id",nullable = false)
    private Long role_id;

    public Role_User(Long id, Long user_id, Long role_id) {
        this.id=id;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public Role_User() {

    }
}
