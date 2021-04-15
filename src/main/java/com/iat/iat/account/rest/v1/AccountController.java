package com.iat.iat.account.rest.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.model.Iat;
import com.iat.iat.account.model.IatAccount;
import com.iat.iat.account.model.IatHistory;
import com.iat.iat.account.service.DepositService;
import com.iat.iat.account.service.IatAccountService;
import com.iat.iat.account.service.IatHistoryService;
import com.iat.iat.account.service.IatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired IatAccountService iatAccountService;
    @Autowired DepositService depositService;
    @Autowired IatService iatService;
    @Autowired IatHistoryService iatHistoryService;

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

    @GetMapping("/admin/iatHistory/{date}")
    public ResponseEntity<List<IatHistory>> getByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){

        return new ResponseEntity<>(iatHistoryService.getByDate(date), HttpStatus.OK);
    }

    @GetMapping("/admin/iatHistory/{date1}/{date2}")
    public ResponseEntity<List<IatHistory>> getByRange(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2){

        return new ResponseEntity<>(iatHistoryService.getByRange(date1, date2), HttpStatus.OK);
    }
    @GetMapping("/admin/iatHistory/{isp}/{date1}/{date2}")
    public ResponseEntity<List<IatHistory>> getByRangeAndIsp(@PathVariable String isp,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2){

        return new ResponseEntity<>(iatHistoryService.getByRangeIsp(isp, date1, date2), HttpStatus.OK);
    }


    //@GetMapping("/verify")
    //    public ResponseEntity<String> verifyFW(@RequestParam int tx_ref, @RequestParam(required = false) String transaction_id,
    //                                               @RequestParam String status ) throws JsonProcessingException {
    //        return new ResponseEntity<>(paymentService.verifyFw(tx_ref,transaction_id,status), HttpStatus.OK);
    //    }

}
