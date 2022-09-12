package com.example.librarymanagment.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "assets")

public class Assets {
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

    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Teams teams;

    @JsonManagedReference
    @OneToMany(mappedBy = "assets")
    private Set<Transactions> transactions=new HashSet<Transactions>();
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

}
