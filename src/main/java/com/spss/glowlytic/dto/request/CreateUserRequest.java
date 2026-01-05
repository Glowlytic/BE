package com.spss.glowlytic.dto.request;

import com.spss.glowlytic.common.constant.PredefinedConstants;
import com.spss.glowlytic.enums.GenderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50)
    @Pattern(regexp = PredefinedConstants.NAME_REGEX, message = "Username must contain only letters and numbers")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password must be at least 5 characters")
    private String password;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = PredefinedConstants.NAME_REGEX, message = "First Name must contain only letters and numbers")
    private String firstName;

    @Pattern(regexp = PredefinedConstants.NAME_REGEX, message = "Middle Name must contain only letters and numbers")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = PredefinedConstants.NAME_REGEX, message = "Last Name must contain only letters and numbers")
    private String lastName;

    private String phoneNumber;

    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    private GenderType gender;

}
