package com.benseddik.book.auth;

import com.benseddik.book.utils.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link com.benseddik.book.user.User}
 */
public record RegisterRequest(        @NotEmpty(message = "Firstname is mandatory")
                                      @NotBlank(message = "Firstname is mandatory")
                                      String firstName,
                                      @NotEmpty(message = "Lastname is mandatory")
                                      @NotBlank(message = "Lastname is mandatory")
                                      String lastName,
                                      @NotEmpty(message = "Email is mandatory")
                                      @NotBlank(message = "Email is mandatory")
                                      @Email(message = "Email is invalid")
                                      String email,
                                      @NotEmpty(message = "Password is mandatory")
                                      @NotBlank(message = "Password is mandatory")
                                      @Password(message = "Password should be valid")
                                      String password) implements Serializable {
}