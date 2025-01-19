package com.example.Book.Store.Application.requestdto;

import com.example.Book.Store.Application.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationRequest {
    @NotBlank(message = "First Name is mandatory")
    private String fName;

    @NotBlank(message = "Last Name is mandatory")
    private String lName;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be a valid Gmail address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$^&*._-]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @Past(message = "Date of Birth must be in the past")
    @NotBlank(message = "Date of Birth is mandatory")
    private String dob;

    @NotBlank(message = "Role is mandatory")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role must be either ADMIN or USER")
    private Role role;

    public @NotBlank(message = "First Name is mandatory") String getfName() {
        return fName;
    }

    public void setfName(@NotBlank(message = "First Name is mandatory") String fName) {
        this.fName = fName;
    }

    public @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be a valid Gmail address") String getEmail() {
        return email;
    }

    public void setEmail(@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be a valid Gmail address") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Last Name is mandatory") String getlName() {
        return lName;
    }

    public void setlName(@NotBlank(message = "Last Name is mandatory") String lName) {
        this.lName = lName;
    }

    public @NotBlank(message = "Password is mandatory") @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$^&*._-]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is mandatory") @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$^&*._-]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character") String password) {
        this.password = password;
    }

    public @Past(message = "Date of Birth must be in the past") @NotBlank(message = "Date of Birth is mandatory") String getDob() {
        return dob;
    }

    public void setDob(@Past(message = "Date of Birth must be in the past") @NotBlank(message = "Date of Birth is mandatory") String dob) {
        this.dob = dob;
    }

    public @NotBlank(message = "Role is mandatory") @Pattern(regexp = "^(ADMIN|USER)$", message = "Role must be either ADMIN or USER") Role getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role is mandatory") @Pattern(regexp = "^(ADMIN|USER)$", message = "Role must be either ADMIN or USER") Role role) {
        this.role = role;
    }
}
