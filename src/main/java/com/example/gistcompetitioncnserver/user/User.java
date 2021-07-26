package com.example.gistcompetitioncnserver.user;

import com.example.gistcompetitioncnserver.registration.token.EmailConfirmationToken;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@EqualsAndHashCode
@Data
public class User implements UserDetails {

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

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean locked = false;

    private boolean enabled = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "emailTokenId")
    private List<EmailConfirmationToken> emailConfirmationTokens;

    public User(String username, String email,
                String userId, String userPassword, UserRole userRole) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userRole = userRole;

    }

    // user detail in spring security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);  }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
