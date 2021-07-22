package com.example.gistcompetitioncnserver.registration.token;

import com.example.gistcompetitioncnserver.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EmailConfirmationToken {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "token_id")
    private Long emailTokenId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    private LocalDateTime confirmedAt;

    // One user can have many confirmation token.
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "id"
    )
    private User user;

}
