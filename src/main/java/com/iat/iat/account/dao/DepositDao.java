package com.iat.iat.account.dao;

import com.iat.iat.account.model.Deposit;
import com.iat.iat.payment.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositDao extends JpaRepository<Deposit, Integer> {
    Deposit findByPaymentMethod(PaymentMethod paymentMethod);
    boolean existsByPaymentMethod(PaymentMethod paymentMethod);
}
