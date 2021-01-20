package com.sarief.user_data_mock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "user_information")
@Data
public class UserInformation {

    @Id
    @NotNull
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private String id;

    @NotNull
    @Column(name = "password", nullable = false)
    private String ranking;

}
