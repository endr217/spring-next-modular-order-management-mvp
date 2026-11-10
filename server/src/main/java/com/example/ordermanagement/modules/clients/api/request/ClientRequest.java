package com.example.ordermanagement.modules.clients.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRequest(
    @NotBlank(message = "{client.name_required}") @Size(max = 150, message = "{client.name_size}")
        String name,
    @NotBlank(message = "{client.email_required}")
        @Email(message = "{client.email_invalid}")
        @Size(max = 150, message = "{client.email_size}")
        String email,
    @Pattern(regexp = "^$|^\\+?[0-9 ]{6,20}$", message = "{client.phone_invalid}") String phone,
    @Size(max = 255, message = "{client.address_size}") String address) {}
