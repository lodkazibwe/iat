package com.iat.iat.isp.rest.v1;

import com.iat.iat.isp.converter.UserDataConverter;
import com.iat.iat.isp.dto.ContactStatusDto;
import com.iat.iat.isp.dto.UserDataDto;
import com.iat.iat.isp.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/userData")
public class UserDataController {
    @Autowired UserDataService userDataService;
    @Autowired UserDataConverter userDataConverter;

    @PostMapping("/admin/add")
    public ResponseEntity<UserDataDto> addUserData(@RequestBody UserDataDto userDataDto){
        return new ResponseEntity<>(userDataConverter.entityToDto(userDataService.addUserData(userDataDto)), HttpStatus.OK);

    }

    @GetMapping("/getByContact/{contact}")
    public ResponseEntity<UserDataDto> getUserData(@PathVariable String contact){
        return new ResponseEntity<>(userDataConverter.entityToDto(userDataService.getUserData(contact)), HttpStatus.OK);
    }

    @GetMapping("admin/getById/{id}")
    public ResponseEntity<UserDataDto> getUserData(@PathVariable int id){
        return new ResponseEntity<>(userDataConverter.entityToDto(userDataService.getUserData(id)), HttpStatus.OK);
    }

    @GetMapping("/all/checkContact/{contact}")
    public ResponseEntity<ContactStatusDto> isContactAvailable(@PathVariable String contact){
        return new ResponseEntity<>(userDataService.isContactAvailable(contact), HttpStatus.OK);
    }

}
