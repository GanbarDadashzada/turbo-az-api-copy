package com.carzapi.digital.controller;

import com.carzapi.digital.model.dto.VerificationDto;
import com.carzapi.digital.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car-zapi/auth")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/send-verification")
    public void sendVerification (@RequestBody VerificationDto verificationDto) {
        loginService.sendVerification(verificationDto);
    }

//    @PostMapping("/confirm-verification")
//    public void confirmVerification (@PathVariable String email) {
//        loginService.sendVerification(email);
//    }
}
