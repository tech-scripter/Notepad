package com.notepad.app.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class UserRequest implements Serializable {
    @Email(
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Email should has @ and . before the top level domain"
    )
    @NotNull(message = "Email should not be null")
    @NotEmpty(message = "Email should not be empty")
    private final String email;

    @NotNull(message = "Password should not be null")
    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password should contain:" +
                    " 1. At least one digit" +
                    " 2. At least one lowercase letter" +
                    " 3. At least one uppercase letter" +
                    " 4. At least one special character (@#$%^&+=)" +
                    " 5. Anything, at least 8 places length")
    private final String password;
}
