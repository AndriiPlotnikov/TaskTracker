package com.sarief.task_tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(schema = "public", name = "comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator__comment_id")
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name="task_id", nullable=false)
    private Task task;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column
    private String content;

}
