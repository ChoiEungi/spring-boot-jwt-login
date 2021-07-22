package com.example.gistcompetitioncnserver.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private long id;

    private String username;

    private String email;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    @JsonIgnore
    private String userPassword;

    private UserRole userRole;

    private boolean enabled;

}
