package com.iat.iat.wallet.rest.v1;

import com.iat.iat.wallet.converter.WalletConverter;
import com.iat.iat.wallet.dto.WalletDto;
import com.iat.iat.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired WalletConverter walletConverter;
    @Autowired WalletService walletService;

    @GetMapping("/myWallet")
    public ResponseEntity<WalletDto> myWallet(){
        return new ResponseEntity<>(walletConverter.entityToDto(walletService.myWallet()), HttpStatus.OK);
    }


}
