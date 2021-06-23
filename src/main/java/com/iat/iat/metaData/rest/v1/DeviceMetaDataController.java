package com.iat.iat.metaData.rest.v1;

import com.iat.iat.metaData.model.DeviceMetaData;
import com.iat.iat.metaData.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/metaData")
public class DeviceMetaDataController {
    @Autowired MetaDataService metaDataService;

    @GetMapping("/admin/getAll")
    public ResponseEntity<List<DeviceMetaData>> getAllUsers(){
        return new ResponseEntity<>(metaDataService.getAll(), HttpStatus.OK);

    }


}
