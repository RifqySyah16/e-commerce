package com.rifqy.project.ecommerce.e_commerce.applicationuser;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.ApplicationUser;
import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.dto.RegisterationResponseDTO;
import com.rifqy.project.ecommerce.e_commerce.authentication.model.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
@RequestMapping("/application-users")
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;

    @GetMapping
    public ResponseEntity<RegisterationResponseDTO> getOne(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();

        ApplicationUser existingApplicationUser = this.applicationUserService.getOne(userId);
        RegisterationResponseDTO registerationResponseDTO = existingApplicationUser.convertToResponse();

        return ResponseEntity.ok(registerationResponseDTO);
    }

}
