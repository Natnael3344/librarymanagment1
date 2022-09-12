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
@Entity
@Table(name = "transactions")
public class Transactions {

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
    private int isReturned;

    @Column(name = "returnDate")
    private LocalDateTime returnDate;

    @Column(name = "returnedOn")
    private LocalDateTime returnedOn;

    @Column(name = "issueDate")
    private LocalDateTime issueDate;

    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Assets assets;

    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Teams teams;

    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Users users1;

    public Transactions(Long id, LocalDateTime created_at, LocalDateTime updated_at, int isReturned, LocalDateTime returnDate, LocalDateTime returnedOn, LocalDateTime issueDate, Assets assets, Teams teams, Users users, Users users1) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.isReturned = isReturned;
        this.returnDate = returnDate;
        this.returnedOn = returnedOn;
        this.issueDate = issueDate;
        this.assets = assets;
        this.teams = teams;
        this.users = users;
        this.users1 = users1;
    }
}
