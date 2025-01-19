package com.example.Book.Store.Application.requestdto;

import com.example.Book.Store.Application.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "First Name is mandatory")
    private String fName;

    @NotBlank(message = "Last Name is mandatory")
    private String lName;

    @Past(message = "Date of Birth must be in the past")
    @NotBlank(message = "Date of Birth is mandatory")
    private LocalDate dob;

    @NotBlank(message = "Role is mandatory")
    private Role role;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$^&*._-]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    public @NotBlank(message = "First Name is mandatory") String getfName() {
        return fName;
    }

    public void setfName(@NotBlank(message = "First Name is mandatory") String fName) {
        this.fName = fName;
    }


    public @NotBlank(message = "Last Name is mandatory") String getlName() {
        return lName;
    }

    public void setlName(@NotBlank(message = "Last Name is mandatory") String lName) {
        this.lName = lName;
    }

    public String getDob() {
        return "dob";
    }

    public void setDob(@Past(message = "Date of Birth must be in the past") @NotBlank(message = "Date of Birth is mandatory") LocalDate dob) {
        this.dob = dob;
    }

    public @NotBlank(message = "Role is mandatory") Role getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role is mandatory") Role role) {
        this.role = role;
    }

    public @NotBlank(message = "Password is mandatory") @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$^&*._-]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is mandatory") @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$^&*._-]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character") String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", dob=" + dob +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}
