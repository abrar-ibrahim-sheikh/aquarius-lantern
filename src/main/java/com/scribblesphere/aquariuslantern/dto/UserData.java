package com.scribblesphere.aquariuslantern.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
@Data
public class UserData implements Serializable {

    private Long userId;

    @NotEmpty
    private String name;

    @Email(message = "Not a valid email address")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Password must have minimum of 8 characters")
    // @Pattern to allow
    private String password;

    @NotNull
    private String about;

}
