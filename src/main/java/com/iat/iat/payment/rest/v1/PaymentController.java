package com.iat.iat.payment.rest.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.iat.iat.flutterWave.FlutterResp;
import com.iat.iat.payment.converter.PaymentConverter;
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

    @PutMapping("/verify")
    public ResponseEntity<PaymentDto> verifyFW(@RequestParam int tx_ref, @RequestParam String transaction_id,
                                               @RequestParam String status ) throws JsonProcessingException {
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.verifyFw(tx_ref,transaction_id,status)), HttpStatus.OK);
    }

    @GetMapping("/myPayments/{date}")
    public ResponseEntity<List<PaymentDto>> myPayments(
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") Date date){
        return new ResponseEntity<>(paymentConverter.entityToDto(paymentService.myDatePayments(date)), HttpStatus.OK);

    }





}
