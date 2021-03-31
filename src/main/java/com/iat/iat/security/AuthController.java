package com.iat.iat.security;


import com.iat.iat.exceptions.InvalidValuesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("get/token")
    public ResponseEntity<?> createAuthToken(@Valid @RequestBody AuthRequest authRequest){

            try {
                logger.info("authenticating....");
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
                );
            } catch (Exception e) {
                logger.info("invalid user name or pass....");
                throw new InvalidValuesException("Incorrect name or password");

            }
            logger.info("getting user details....");
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUserName());
        logger.info("getting token....");

            final String jwt = jwtUtil.generateToken(userDetails);
            logger.info("authenticated....");
            return new ResponseEntity<>(new AuthResponse(jwt), HttpStatus.OK);
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


