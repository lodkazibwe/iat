package com.iat.iat.flutterWave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class PayGatewayController {
    @Autowired FlutterWaveService flutterWaveService;


   /* public ResponseEntity<FlutterResp> initiate(@PathVariable double amount){
        return new ResponseEntity<>(flutterWaveService.initiate(amount), HttpStatus.OK);
    }*/


}
