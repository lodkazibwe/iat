package com.iat.iat.wallet.rest.v1;

import com.iat.iat.wallet.converter.WalletConverter;
import com.iat.iat.wallet.dto.WalletDto;
import com.iat.iat.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired WalletConverter walletConverter;
    @Autowired WalletService walletService;
    //@Autowired FlutterWaveService flutterWaveService;


   /* @PostMapping("/initiate/{amount}")
    public ResponseEntity<FlutterResp> initiate(@PathVariable double amount){
        return new ResponseEntity<>(flutterWaveService.initiate(amount), HttpStatus.OK);
    }*/

    @GetMapping("/myWallet")
    public ResponseEntity<WalletDto> myWallet(){
        return new ResponseEntity<>(walletConverter.entityToDto(walletService.myWallet()), HttpStatus.OK);
    }

    @GetMapping("/admin/getAll")
    public ResponseEntity<List<WalletDto>> getAll(){
        return new ResponseEntity<>(walletConverter.entityToDto(walletService.getAll()), HttpStatus.OK);
    }


}
