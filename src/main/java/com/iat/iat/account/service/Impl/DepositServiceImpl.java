package com.iat.iat.account.service.Impl;

import com.iat.iat.account.dao.DepositDao;
import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.service.DepositService;
import com.iat.iat.exceptions.InvalidValuesException;
import com.iat.iat.payment.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceImpl implements DepositService {
    @Autowired DepositDao depositDao;
    @Override
    public Deposit addDeposit(Deposit depositAccount) {
        boolean bool =depositDao.existsByPaymentMethod(depositAccount.getPaymentMethod());
        if(bool){
            throw new InvalidValuesException("account exists: "+depositAccount.getPaymentMethod());
        }
            Deposit deposit =new Deposit();
            deposit.setPaymentMethod(depositAccount.getPaymentMethod());
            deposit.setAmount(0);
        return depositDao.save(deposit);
    }

    @Override
    public Deposit updateDeposit(Deposit deposit) {
        Deposit depositAccount=depositDao.findByPaymentMethod(PaymentMethod.FLUTTER_WAVE);
        depositAccount.setAmount(depositAccount.getAmount()+deposit.getAmount());
        return depositDao.save(depositAccount);
    }



    @Override
    public Deposit getDeposit(int id) {
        return null;
    }

    @Override
    public Deposit getByMethod(PaymentMethod paymentMethod) {
        return depositDao.findByPaymentMethod(paymentMethod);
    }

    public boolean existByMethod(PaymentMethod method){
        return depositDao.existsByPaymentMethod(method);
    }
}
