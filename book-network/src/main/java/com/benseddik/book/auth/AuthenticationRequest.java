package com.benseddik.book.auth;

import com.benseddik.book.utils.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AuthenticationRequest(@NotEmpty(message = "Email is mandatory")
                                    @NotBlank(message = "Email is mandatory")
                                    @Email(message = "Email is invalid")
                                    String email,
                                    @NotEmpty(message = "Password is mandatory")
                                    @NotBlank(message = "Password is mandatory")
                                    @Password(message = "Password should be valid")
                                    String password
) {
}
