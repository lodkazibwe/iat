package com.iat.iat.payment.dao;

import com.iat.iat.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
    List<Payment> findByWalletIdOrderByIdDesc(int walletId);
    List<Payment> findByPaymentDate(Date paymentDate);
    List<Payment> findByPaymentDateAndWalletId(Date paymentDate, int walletId);
    List<Payment> findFirst5ByWalletIdOrderByIdDesc(int walletId);
    List<Payment> findFirst50ByWalletIdOrderByIdDesc(int walletId);


}
