package com.iat.iat.user.rest.v1;

import com.iat.iat.firebase.File.FileService;
import com.iat.iat.security.AuthResponse;
import com.iat.iat.security.MyUserDetailsService;
import com.iat.iat.user.converter.UserConverter;
import com.iat.iat.user.dto.ChangePassDto;
import com.iat.iat.user.dto.UserDto;
import com.iat.iat.user.dto.UserInfo;
import com.iat.iat.user.service.UserService;
import com.iat.iat.wallet.converter.WalletConverter;
import com.iat.iat.wallet.dto.WalletDto;
import com.iat.iat.wallet.model.Wallet;
import com.iat.iat.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired WalletService walletService;
    @Autowired UserConverter userConverter;
    @Autowired MyUserDetailsService myUserDetailsService;
    @Autowired WalletConverter walletConverter;
    @Autowired FileService fileService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid UserDto userDto){
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);

    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDto> currentUser(){
        return new ResponseEntity<>(userConverter.entityToDto(myUserDetailsService.currentUser()), HttpStatus.OK);
    }

    @PutMapping("/changePass")
    public ResponseEntity<UserDto> changePassword(@Valid @RequestBody ChangePassDto changePassDto){
        return new ResponseEntity<>(userConverter.entityToDto(userService.changePassword(changePassDto)), HttpStatus.OK);

    }

    @GetMapping("/admin/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id){
        return new ResponseEntity<>(userConverter.entityToDto(userService.getUser(id)), HttpStatus.OK);
    }

    @GetMapping("/admin/getUser/{contact}")
    public ResponseEntity<UserDto> getUserByContact(@PathVariable String contact){
        return new ResponseEntity<>(userConverter.entityToDto(userService.getUser(contact)), HttpStatus.OK);
    }

    @GetMapping("/admin/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userConverter.entityToDto(userService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/admin/userInfo/{uid}")
    public ResponseEntity<UserInfo> userInfo(@PathVariable int uid){
        WalletDto walletDto =walletConverter.entityToDto(walletService.getByUser(uid));
        UserDto userDto =userConverter.entityToDto(userService.getUser(uid));
        return new ResponseEntity<>(new UserInfo(userDto, walletDto), HttpStatus.OK);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<URL> updateImage(@RequestParam("file") MultipartFile file)
            throws IOException {
        return new ResponseEntity<>(fileService.uploadImage(file), HttpStatus.OK);
    }

    @GetMapping("/myImage")
    public ResponseEntity<URL> getImage(){
        return  new ResponseEntity<>(fileService.signedUrl(), HttpStatus.OK);

    }




}
