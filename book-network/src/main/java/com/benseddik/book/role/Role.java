package com.benseddik.book.role;

import com.benseddik.book.common.AbstractAuditingEntity;
import com.benseddik.book.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractAuditingEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -1773008266241902170L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users = new LinkedHashSet<>();

}
