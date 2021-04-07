package com.iat.iat.payment.rest.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.iat.iat.flutterWave.FlutterResp;
import com.iat.iat.payment.converter.PaymentConverter;
import com.iat.iat.payment.dto.BuyIatDto;
import com.iat.iat.payment.dto.PaymentDto;
import com.iat.iat.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired PaymentConverter paymentConverter;

    @PostMapping("/depositFW/{amount}")
    public ResponseEntity<FlutterResp> depositFW(@PathVariable double amount){
        return new ResponseEntity<>(paymentService.depositFW(amount), HttpStatus.OK);
    }


    @GetMapping("/verify")
    public ResponseEntity<String> verifyFW(@RequestParam int tx_ref, @RequestParam(required = false) String transaction_id,
                                               @RequestParam String status ) throws JsonProcessingException {
        return new ResponseEntity<>(paymentService.verifyFw(tx_ref,transaction_id,status), HttpStatus.OK);
    }

    @GetMapping("/myLastPayment")
    public ResponseEntity<PaymentDto> myLastPayment(){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.myLastPayment()), HttpStatus.OK);

    }

    @PostMapping("/buyIat")
    public ResponseEntity<PaymentDto> buyIat(@RequestBody BuyIatDto buyIatDto){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.buyIat(buyIatDto)), HttpStatus.OK);
    }

    @PutMapping("/transfer/{amount}/{contact}")
    public ResponseEntity<PaymentDto> transferToAnotherWallet(@PathVariable double amount, @PathVariable String contact){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.transfer(amount, contact)), HttpStatus.OK);
    }


    @GetMapping("/myLastPayments")
    public ResponseEntity<List<PaymentDto>> last5Payments(){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.myLastFive()), HttpStatus.OK);

    }

    @GetMapping("/myPayments")
    public ResponseEntity<List<PaymentDto>> myPayments(){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.myPayments()), HttpStatus.OK);

    }

    @GetMapping("/admin/allByWallet/{walletId}")
    public ResponseEntity<List<PaymentDto>> getByWallet(@PathVariable int walletId){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.allByWallet(walletId)), HttpStatus.OK);

    }

    @GetMapping("/admin/getByWallet/{walletId}")
    public ResponseEntity<List<PaymentDto>> lastFiftyByWallet(@PathVariable int walletId){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.lastFifty(walletId)), HttpStatus.OK);

    }

    @GetMapping("/admin/getByDate/{date}")
    public ResponseEntity<List<PaymentDto>> getByPaymentDate(
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") Date date){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.getByDate(date)), HttpStatus.OK);

    }

    @GetMapping("/admin/getByDateRange/{date1}/{date2}")
    public ResponseEntity<List<PaymentDto>> getByPaymentDateRange(
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") Date date1,
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") Date date2){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.getByDateRange(date1, date2)), HttpStatus.OK);

    }



}
