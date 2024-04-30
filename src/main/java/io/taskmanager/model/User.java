package io.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.taskmanager.enumeration.Gender;
import io.taskmanager.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String userId;
    @Column(unique = true, nullable = false)
    private String username;
    private String gender;
    @Column(unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Date joinDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String[] authorities;
}
