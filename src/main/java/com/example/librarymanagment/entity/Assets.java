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
@Table(name = "assets")
public class Assets implements Serializable{
    @Id
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
    @Column(name = "name",length = 2000)
    private String name;

    @Column(name = "author",length = 2000)
    private String author;

    @Column(name = "publication",length = 2000)
    private String publication;

    @Column(name = "edition",length = 2000)
    private String edition;
    @Column(name = "cost",length = 2000)
    private String cost;

    @Column(name = "language",length = 2000)
    private String language;

    @Column(name = "pages",length = 2000)
    private String pages;

    @Lob
    @Column(name = "description",length = 20000)
    private String description;

    @Column(name = "rfid_tag",length = 2000)
    private String rfid_tag;

    @Column(name = "danger_level",length = 2000)
    private int danger_level;


    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Teams teams;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "assets", cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
//    private Set<Transactions> transactions;
      @OneToMany(mappedBy="assets")
      private List<Transactions> transactions= new ArrayList<>();

      public Assets(List<Transactions> transactions){
          setTransactions(transactions);
      }
    public Assets(Long id, LocalDateTime created_at, LocalDateTime updated_at,LocalDateTime deleted_at, String name, String author, String publication,String edition, String cost, String language, String pages, String description, String rfid_tag, int danger_level, Teams teams) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at=deleted_at;
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.edition=edition;
        this.cost = cost;
        this.language = language;
        this.pages = pages;
        this.description = description;
        this.rfid_tag = rfid_tag;
        this.danger_level = danger_level;
        this.teams = teams;
    }

    public Assets() {

    }

    public List<Transactions> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transactions> transactions) {
        transactions.forEach(transaction -> transaction.setAssets(this));
        this.transactions = transactions;
    }

    public Transactions addTransactions(Transactions transaction) {
        getTransactions().add(transaction);
        transaction.setAssets(this);

        return transaction;
    }

    public Transactions removeTransactions(Transactions transaction) {
        getTransactions().remove(transaction);
        transaction.setAssets(null);

        return transaction;
    }
}
