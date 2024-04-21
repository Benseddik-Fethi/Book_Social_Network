package com.benseddik.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITokenRepository extends JpaRepository<Token, Long> {
    /**
     * Find token by token
     *
     * @param token the token
     * @return the token
     */
    Optional<Token> findByToken(String token);
}
