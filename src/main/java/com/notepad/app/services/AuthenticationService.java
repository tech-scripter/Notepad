package com.notepad.app.services;

import com.notepad.app.exceptions.UserAlreadyExistsException;
import com.notepad.app.models.Role;
import com.notepad.app.models.User;
import com.notepad.app.payloads.request.UserRequest;
import com.notepad.app.payloads.response.TokenResponse;
import com.notepad.app.payloads.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    private static final Role defaultRole = Role.USER;

    private final JwtEncoder encoder;
    private final ModelMapper mapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public AuthenticationService(JwtEncoder encoder,
                                 ModelMapper mapper,
                                 UserService userService,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationProvider authenticationProvider) {
        this.encoder = encoder;
        this.mapper = mapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
    }

    @Transactional
    public ResponseEntity<?> signUp(UserRequest userRequest) {
        if (userService.existsByEmail(userRequest.getEmail())) {
            String msg = String.format("Email %s already exists", userRequest.getEmail());
            throw new UserAlreadyExistsException(msg);
        }
        User user = mapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(defaultRole);
        user = userService.save(user);
        UserResponse userResponse = mapper.map(user, UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> signIn(UserRequest userRequest) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        userRequest.getEmail(),
                        userRequest.getPassword());
        Authentication authentication = authenticationProvider.authenticate(token);
        TokenResponse tokenResponse = getToken(authentication);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    private TokenResponse getToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        String hash = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenResponse(hash, now, now.plusSeconds(expiry));
    }

}
