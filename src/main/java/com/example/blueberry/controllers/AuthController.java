package com.example.blueberry.controllers;



import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;


import com.example.blueberry.models.User;
import com.example.blueberry.payload.request.LoginRequest;
import com.example.blueberry.payload.response.JwtResponse;
import com.example.blueberry.repository.RoleRepository;
import com.example.blueberry.repository.UserRepository;
import com.example.blueberry.security.jwt.JwtUtils;
import com.example.blueberry.security.services.SequenceGeneratorService;
import com.example.blueberry.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

//  @Autowired
//  RefreshTokenService refreshTokenService;



    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println(loginRequest.getUsername()+"---"+loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());



//    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, "refreshToken", userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(sequenceGeneratorService.getSequenceNumber(User.SEQUENCE_NAME));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }


}
