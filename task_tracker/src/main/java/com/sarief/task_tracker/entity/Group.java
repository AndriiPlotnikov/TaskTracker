package com.sarief.task_tracker.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Table(schema = "public", name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator__group_id")
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column
    private String name;

    @OneToMany(fetch = LAZY, mappedBy = "group")
    private List<User> users;
}
