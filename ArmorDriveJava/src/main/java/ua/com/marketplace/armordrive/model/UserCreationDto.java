package ua.com.marketplace.armordrive.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserCreationDto(
        @NotNull
        @NotEmpty
        String firstName,
        @NotNull
        @NotEmpty
        String lastName,
        @NotNull
        @NotEmpty
        String email,
        @NotNull
        @NotEmpty
        String phoneNumber,
        @NotNull
        @NotEmpty
        String password,
        String matchingPassword){

}
