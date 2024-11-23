package com.rifqy.project.ecommerce.e_commerce.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rifqy.project.ecommerce.e_commerce.applicationuser.ApplicationUserService;
import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.ApplicationUser;
import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.dto.RegisterationRequestDTO;
import com.rifqy.project.ecommerce.e_commerce.authentication.dto.JwtResponseDTO;
import com.rifqy.project.ecommerce.e_commerce.authentication.dto.LoginRequestDTO;
import com.rifqy.project.ecommerce.e_commerce.authentication.jwt.JwtProvider;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final ApplicationUserService applicationUserService;

    @PostMapping("/tokens")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        logger.info("Attemp Login To System");
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponseDTO(jwt));
    }

    @PostMapping("/registerations")
    public ResponseEntity<JwtResponseDTO> registeration(
            @RequestBody @Valid RegisterationRequestDTO registrasionRequestDTO) {
        ApplicationUser newUser = registrasionRequestDTO.convertToEntity();
        this.applicationUserService.save(newUser);

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registrasionRequestDTO.getUsername(),
                        registrasionRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponseDTO(jwt));
    }
}