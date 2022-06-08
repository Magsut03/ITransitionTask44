package com.example.itransitiontask4.controller.auth;


import com.example.itransitiontask4.dto.request.auth.LoginRequestDto;
import com.example.itransitiontask4.dto.request.auth.RegistrationRequestDto;
import com.example.itransitiontask4.service.auth.OauthService;
import com.example.itransitiontask4.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final OauthService oauthService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequestDto registrationRequestDto){
        return ResponseEntity.ok(oauthService.register(registrationRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> registration(@RequestBody LoginRequestDto loginDto){
        return ResponseEntity.ok(oauthService.login(loginDto));
    }

}
