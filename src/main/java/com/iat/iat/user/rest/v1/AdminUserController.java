package com.iat.iat.user.rest.v1;

import com.iat.iat.user.converter.AdminUserConverter;
import com.iat.iat.user.dto.AdminUserDto;
import com.iat.iat.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/adminUser")
public class AdminUserController {
    @Autowired
    AdminUserConverter adminUserConverter;
    @Autowired AdminService adminService;

    @PostMapping("/root/add")
    public ResponseEntity<AdminUserDto> addAdmin(@RequestBody AdminUserDto adminUserDto){
        return new ResponseEntity<>(adminUserConverter.entityToDto(adminService.addAdmin(adminUserDto)), HttpStatus.OK);

    }

    @GetMapping("/admin/getById/{id}")
    public ResponseEntity<AdminUserDto> getAdmin(@PathVariable int id){
        return new ResponseEntity<>(adminUserConverter.entityToDto(adminService.getAdmin(id)), HttpStatus.OK);
    }

    @GetMapping("/admin/getByContact/{contact}")
    public ResponseEntity<AdminUserDto> getAdmin(@PathVariable String contact){
        return new ResponseEntity<>(adminUserConverter.entityToDto(adminService.getAdmin(contact)), HttpStatus.OK);
    }

    @GetMapping("/root/getAll")
    public ResponseEntity<List<AdminUserDto>> getAll(){
        return new ResponseEntity<>(adminUserConverter.entityToDto(adminService.getAll()), HttpStatus.OK);
    }

     }
