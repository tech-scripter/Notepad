package com.thoughtsonline.app.payloads;

import com.thoughtsonline.app.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;

@AllArgsConstructor
public class RegistrationForm {

    @Size(max = 70)
    @NotBlank
    private String nickname;

    @Email
    @Size(max = 70)
    @NotBlank
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
    @NotBlank
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User convertToUserEntity(PasswordEncoder passwordEncoder) {
        return new User(nickname, email, passwordEncoder.encode(password));
    }
}
