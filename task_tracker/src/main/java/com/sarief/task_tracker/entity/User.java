package com.sarief.task_tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

import static javax.persistence.FetchType.LAZY;


@Entity
@Table(schema = "public", name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator__user_id")
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "username", updatable = false, nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "external_id", unique = true)
    private String externalId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="group_id")
    private Group group;

    @Column(name = "registered", updatable = false)
    private Date registered;
}
