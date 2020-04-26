package com.nortal.summeruni.assignment.entity;

import com.nortal.summeruni.assignment.security.BlogUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email // https://stackoverflow.com/questions/4459474/hibernate-validator-email-accepts-askstackoverflow-as-valid
    @NotEmpty
    private String email;

    @NotEmpty
    private  String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String role;

    // private final Set<? extends GrantedAuthority> grantedAuthorities;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }
}
