package com.iat.iat.account.dao;

import com.iat.iat.account.model.DepositHistory;
import com.iat.iat.payment.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DepositHistoryDao  extends JpaRepository<DepositHistory, Integer> {

    List<DepositHistory> findByDate(Date date);
    List<DepositHistory> findByDateGreaterThanEqualAndDateLessThanEqualOrderById(Date date1, Date date2);
    List<DepositHistory> findByPaymentMethodAndDateGreaterThanEqualAndDateLessThanEqualOrderById(PaymentMethod method, Date date1, Date date2);
}
