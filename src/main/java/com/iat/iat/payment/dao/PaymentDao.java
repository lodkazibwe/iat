package com.iat.iat.payment.dao;

import com.iat.iat.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
    List<Payment> findByWalletIdAndStatusOrderByIdDesc(int walletId, String status);
    List<Payment> findByWalletIdOrderByIdDesc(int walletId);

    List<Payment> findByPaymentDateAndStatus(Date paymentDate, String status);
    List<Payment> findByPaymentDateGreaterThanEqualAndPaymentDateLessThanEqualAndStatus(Date date1, Date date2, String status);

    List<Payment> findByPaymentDateAndWalletId(Date paymentDate, int walletId);

    List<Payment> findFirst5ByWalletIdAndStatusOrderByIdDesc(int walletId, String status);

    List<Payment> findFirst50ByWalletIdAndStatusOrderByIdDesc(int walletId, String status);


}
