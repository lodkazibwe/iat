package com.iat.iat.account.rest.v1;

import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.model.Iat;
import com.iat.iat.account.model.IatAccount;
import com.iat.iat.account.service.DepositService;
import com.iat.iat.account.service.IatAccountService;
import com.iat.iat.account.service.IatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired IatAccountService iatAccountService;
    @Autowired DepositService depositService;
    @Autowired IatService iatService;

    @GetMapping("/myIat")
    public ResponseEntity<IatAccount> myIatAccount(){
        return new ResponseEntity<>(iatAccountService.myIatAccount(), HttpStatus.OK);
    }

    @GetMapping("/admin/getIatAccount/{contact}")
    public ResponseEntity<IatAccount> getIatUserAccount(@PathVariable String contact){
        return new ResponseEntity<>(iatAccountService.getAccount(contact), HttpStatus.OK);
    }

    @GetMapping("/admin/allIatAccounts")
    public ResponseEntity<List<IatAccount>> getAllIatUserAccounts(){
        return new ResponseEntity<>(iatAccountService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/admin/allDeposit")
    public ResponseEntity<List<Deposit>> getAllDepositAccounts(){
        return new ResponseEntity<>(depositService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/admin/allIat")
    public ResponseEntity<List<Iat>> getAllIatPaidAccounts(){
        return new ResponseEntity<>(iatService.getAll(), HttpStatus.OK);
    }

    //@GetMapping("/verify")
    //    public ResponseEntity<String> verifyFW(@RequestParam int tx_ref, @RequestParam(required = false) String transaction_id,
    //                                               @RequestParam String status ) throws JsonProcessingException {
    //        return new ResponseEntity<>(paymentService.verifyFw(tx_ref,transaction_id,status), HttpStatus.OK);
    //    }
}
