package com.benseddik.book.user;

import com.benseddik.book.book.Book;
import com.benseddik.book.common.AbstractAuditingEntity;
import com.benseddik.book.history.BookTransactionHistory;
import com.benseddik.book.role.Role;
import com.benseddik.book.utils.annotation.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User extends AbstractAuditingEntity implements UserDetails, Principal {
    @Serial
    private static final long serialVersionUID = 5336770561731211311L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Past
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @NotBlank
    @Column(name = "password", nullable = false)
    @Password(message = "Password should be valid")
    private String password;
    private boolean accountLocked;
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    private Set<Book> books = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<BookTransactionHistory> bookTransactionHistories = new LinkedHashSet<>();

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String fullName() {
        return firstName + " " + lastName;
    }
}
