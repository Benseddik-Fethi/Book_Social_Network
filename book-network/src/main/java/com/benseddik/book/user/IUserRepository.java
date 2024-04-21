package com.benseddik.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by email
     *
     * @param email the email of the user
     * @return the user
     */
    Optional<User> findByEmail(String email);
}
