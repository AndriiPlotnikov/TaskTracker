package com.sarief.task_tracker.entity;

import com.sarief.task_tracker.entity.converter.StringListConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Table(schema = "public", name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator__task_id")
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Column
    private String description;

    @OneToMany(fetch = LAZY, mappedBy = "task")
    private List<Comment> comments;

    @Convert(converter = StringListConverter.class)
    private List<String> attachmentLinks;

    @Column(name = "created", updatable = false)
    private Date created;

}
