package com.houbenz.redditclone.controller;


import com.houbenz.redditclone.dto.RegisterRequest;
import com.houbenz.redditclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest){

        authService.signUp(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }


    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifiyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<String>("Account activated !",HttpStatus.OK);
    }


    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }
}
