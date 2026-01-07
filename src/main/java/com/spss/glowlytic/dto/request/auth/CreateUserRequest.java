package com.spss.glowlytic.dto.request.auth;

import com.spss.glowlytic.common.constants.PredefinedConstants;
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

    @NotBlank(message = "EMAIL_REQUIRED")
    @Email(message = "INVALID_EMAIL")
    private String email;

    @NotBlank(message = "USERNAME_REQUIRED")
    @Size(min = 3, max = 50, message = "USERNAME_INVALID_SIZE")
    @Pattern(regexp = PredefinedConstants.NAME_REGEX, message = "USERNAME_INVALID")
    private String username;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 5, message = "PASSWORD_TOO_SHORT")
    private String password;

    @NotBlank(message = "FIRST_NAME_REQUIRED")
    @Pattern(regexp = PredefinedConstants.NAME_REGEX, message = "INVALID_FIRST_NAME")
    private String firstName;

    @Pattern(regexp = PredefinedConstants.NAME_REGEX, message = "INVALID_MIDDLE_NAME")
    private String middleName;

    @NotBlank(message = "LAST_NAME_REQUIRED")
    @Pattern(regexp = PredefinedConstants.NAME_REGEX, message = "INVALID_LAST_NAME")
    private String lastName;

    private String phoneNumber;

    private LocalDate dob;

    @NotBlank(message = "GENDER_REQUIRED")
    private GenderType gender;

}
