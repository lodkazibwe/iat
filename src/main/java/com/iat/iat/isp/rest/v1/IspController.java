package com.iat.iat.isp.rest.v1;

import com.iat.iat.isp.converter.ISPConverter;
import com.iat.iat.isp.dto.ISPDto;
import com.iat.iat.isp.service.ISPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/isp")
public class IspController {
    @Autowired ISPService ispService;
    @Autowired
    ISPConverter ispConverter;

    @PostMapping("/admin/add")
    public ResponseEntity<ISPDto> addNew(@RequestBody ISPDto ispDto){
        return new ResponseEntity<>(ispConverter.entityToDto(ispService.addIsp(ispDto)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ISPDto>> getAll(){
        return new ResponseEntity<>(ispConverter.entityToDto(ispService.getAll()), HttpStatus.OK);
    }


}
