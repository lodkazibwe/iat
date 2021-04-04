package com.iat.iat.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

   @Autowired AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("get/token")
    public ResponseEntity<AuthResponse> createAuthToken(@Valid @RequestBody AuthRequest authRequest){

            return new ResponseEntity<>(new AuthResponse(authService.getJwt(authRequest)), HttpStatus.OK);
        }

    @PostMapping("getToken")
    public ResponseEntity<AuthResponseV2> Authenticate(@Valid @RequestBody AuthRequest authRequest){

        return new ResponseEntity<>(authService.authenticate(authRequest), HttpStatus.OK);
    }


    /*@PutMapping("/requestPin/{contact}")//admin
    public ResponseEntity<?> requestPin(@PathVariable String contact){
        userService.requestPin(contact);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/createPass")//authenticated
    public ResponseEntity<?> createPass(@Valid @RequestBody AuthRequest authRequest){
        userService.createPass(authRequest.getPassword());
        return new ResponseEntity<>("success", HttpStatus.OK);

    }*/



    }


