package com.example.librarymanagment.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "transactions")
public class Transactions implements Serializable {

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

    @Column(name = "isReturned")
    private String isReturned;

    @Column(name = "returnDate")
    private String returnDate;

    @Column(name = "returnedOn")
    private String returnedOn;

    @Column(name = "issueDate")
    private String issueDate;



    @ManyToOne
    @JoinColumn(name = "assets_id")
    private Assets assets;

    public Assets getAssets() {
        return this.assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }


    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name = "team_id",referencedColumnName = "id")
    @JsonIgnore
    private Teams teams;


    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name = "member_id",referencedColumnName = "id")
    @JsonIgnore
    private Users users;

    public Transactions(Assets assets,Long id, LocalDateTime created_at, LocalDateTime updated_at, String isReturned, String returnDate, String returnedOn, String issueDate, Teams teams, Users users) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.isReturned = isReturned;
        this.returnDate = returnDate;
        this.returnedOn = returnedOn;
        this.issueDate = issueDate;
        this.teams = teams;
        this.users = users;

    }

    public Transactions() {

    }
}
