package com.iat.iat.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iat.iat.flutterWave.FlutterResp;
import com.iat.iat.payment.dto.PaymentDto;
import com.iat.iat.payment.model.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentService {
    FlutterResp depositFW(double amount);
    Payment verifyFw(int tx_ref, String transactionId, String status) throws JsonProcessingException;
    List<Payment> myDatePayments(Date paymentDate);
    Payment getById(int id);
    List<Payment> getByDate(Date paymentDate);
    List<Payment> getByWallet(int walletId);
    Payment updatePayment(PaymentDto paymentDto);
    void deletePayment(int id);

    Payment myLastPayment();
}
