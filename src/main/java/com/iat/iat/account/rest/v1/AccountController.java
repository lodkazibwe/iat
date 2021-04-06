package com.iat.iat.account.rest.v1;

import com.iat.iat.account.model.IatAccount;
import com.iat.iat.account.service.IatAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired IatAccountService iatAccountService;

    @GetMapping("/myIat")
    public ResponseEntity<IatAccount> myIatAccount(){
        return new ResponseEntity<>(iatAccountService.myIatAccount(), HttpStatus.OK);
    }
    //@GetMapping("/verify")
    //    public ResponseEntity<String> verifyFW(@RequestParam int tx_ref, @RequestParam(required = false) String transaction_id,
    //                                               @RequestParam String status ) throws JsonProcessingException {
    //        return new ResponseEntity<>(paymentService.verifyFw(tx_ref,transaction_id,status), HttpStatus.OK);
    //    }
}
