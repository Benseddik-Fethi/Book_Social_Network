package com.benseddik.book.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find role by name
     *
     * @param name the name of the role
     * @return the role
     */
    Optional<Role> findByName(String name);
}
