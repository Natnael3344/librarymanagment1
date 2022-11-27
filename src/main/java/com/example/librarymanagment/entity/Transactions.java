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

    @Column(name = "isReturned",columnDefinition = "TINYINT(4)",nullable = false)
    private Integer isReturned;

    @Column(name = "returnDate",nullable = false)
    private String returnDate;

    @Column(name = "returnedOn")
    private String returnedOn;

    @Column(name = "issueDate",nullable = false)
    private String issueDate;



//    @ManyToOne
    @Column(name = "asset_id",nullable = false)
    private Integer asset_id;

//    public Assets getAssets() {
//        return this.assets;
//    }

//    public void setAssets(Assets assets) {
//        this.assets = assets;
//    }


//    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @Column(name = "team_id",nullable = true)
//    @JsonIgnore
    private Integer team_id;

    @Column(name = "user_id")
    private Integer user_id;

//    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @Column(name = "member_id")
//    @JsonIgnore
    private Integer member_id;

    public Transactions(Long id, LocalDateTime created_at, LocalDateTime updated_at, Integer isReturned, String returnDate, String returnedOn, String issueDate,Integer asset_id, Integer team_id, Integer user_id, Integer member_id) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.isReturned = isReturned;
        this.returnDate = returnDate;
        this.returnedOn = returnedOn;
        this.issueDate = issueDate;
        this.asset_id = asset_id;
        this.team_id = team_id;
        this.member_id=member_id;
        this.user_id=user_id;

    }

    public Transactions() {

    }
}
