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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "users")
public class Users {


    @Id
    @GeneratedValue
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
    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "mobile",unique = true)
    private String mobile;

    @Column(name = "email_verified_at")
    private LocalDateTime email_verified_at;

    @Column(name = "password")
    private String password;

    @Column(name = "remember_token")
    private String remember_token;

    @Column(name = "rfid_tag", unique = true)
    private String rfid_tag;


    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name="team_id")
    @JsonIgnore
    private Teams teams;


    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Set<Role_User> role_user;


    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Set<Transactions> transactions;
    public Users(Long id, LocalDateTime created_at, LocalDateTime updated_at, String fname, String lname, String email, String mobile, LocalDateTime email_verified_at, String password, String remember_token, String rfid_tag, Teams teams) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobile = mobile;
        this.email_verified_at = email_verified_at;
        this.password = password;
        this.remember_token = remember_token;
        this.rfid_tag = rfid_tag;
        this.teams = teams;
    }

    public Users() {

    }
//    public void addRole_User(Role_User role_users) {
//        this.role_user.add(role_users);
//        role_users.getUsers().add(this);
//    }
//
//    public void removeRole_User(Role_User role_users) {
//        this.role_user.remove(role_users);
//        role_users.getUsers().remove(this);
//    }
//
//    public void addTransactions(Transactions transaction) {
//        this.transactions.add(transaction);
//        transaction.getUsers().add(this);
//    }
//    public void removeTransactions(Transactions transaction) {
//        this.transactions.remove(transaction);
//        transaction.getUsers().remove(this);
//    }

}
