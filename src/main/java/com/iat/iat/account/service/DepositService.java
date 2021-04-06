package com.iat.iat.account.service;

import com.iat.iat.account.model.Deposit;
import com.iat.iat.payment.model.PaymentMethod;

import java.util.List;

public interface DepositService {
    Deposit addDeposit(Deposit deposit);
    Deposit updateDeposit(Deposit deposit);
    Deposit getDeposit(int id);
    Deposit getByMethod(PaymentMethod paymentMethod);
    boolean existByMethod(PaymentMethod method);
    List<Deposit> getAll();
}
