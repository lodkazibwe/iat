package com.iat.iat.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iat.iat.flutterWave.FlutterResp;
import com.iat.iat.isp.dto.ISPDto;
import com.iat.iat.isp.model.ISP;
import com.iat.iat.payment.dto.BuyIatDto;
import com.iat.iat.payment.dto.PaymentDto;
import com.iat.iat.payment.model.IatPackage;
import com.iat.iat.payment.model.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentService {
    FlutterResp depositFW(double amount);
    String verifyFw(int tx_ref, String transactionId, String status) throws JsonProcessingException;
    List<Payment> myDatePayments(Date paymentDate);
    Payment getById(int id);
    List<Payment> getByDate(Date paymentDate);
    List<Payment> getByDateRange(Date date1, Date date2);
    List<Payment> allByWallet(int walletId);
    Payment updatePayment(PaymentDto paymentDto);
    void deletePayment(int id);

    Payment buyIat(BuyIatDto buyIatDto);
    Payment transfer(double amount, String contact);
    Payment myLastPayment();
    List<Payment> myPayments();
    List<Payment> myLastFive();
    List<Payment> lastFifty(int walletId);
}
