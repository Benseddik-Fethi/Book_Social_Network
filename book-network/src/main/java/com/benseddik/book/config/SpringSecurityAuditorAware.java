package com.benseddik.book.config;

import com.benseddik.book.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Implementation of AuditorAware based on Spring Security.
 *
 * @author Fethi Benseddik
 */
@Slf4j
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    /**
     * Method called by Spring Security to automatically get the current auditor (username).
     * Used by Spring Data JPA to automatically set the current auditor (username) when saving a new object.
     *
     * @return the current auditor (username)
     * @author Fethi Benseddik
     * @see AuditorAware
     * @see org.springframework.data.jpa.repository.config.EnableJpaAuditing
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.of("aa000000-a0a0-0000-a000-0a00a0aaa0aa");
        }
        User userPrincipal = (User) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getUuid().toString());
    }
}
