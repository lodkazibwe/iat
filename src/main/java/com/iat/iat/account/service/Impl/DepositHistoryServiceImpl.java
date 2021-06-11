package com.iat.iat.account.service.Impl;

import com.iat.iat.account.dao.DepositHistoryDao;
import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.model.DepositHistory;
import com.iat.iat.account.service.DepositHistoryService;
import com.iat.iat.account.service.DepositService;
import com.iat.iat.payment.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DepositHistoryServiceImpl implements DepositHistoryService {
    @Autowired DepositHistoryDao depositHistoryDao;
    @Autowired DepositService depositService;


    @Transactional
    @Scheduled(cron = "30 59 23 * * *",zone = "EAT")
    public void getCurrentIat(){

        List<Deposit> depositList =depositService.getAll();
        List<DepositHistory> depositHistoryList = new ArrayList<>();
        for(Deposit deposit:depositList){
            DepositHistory depositHistory =new DepositHistory();
            depositHistory.setAmount(deposit.getAmount());
            depositHistory.setDate(new Date());
            depositHistory.setPaymentMethod(deposit.getPaymentMethod());
            depositHistoryList.add(depositHistory);
            depositService.reset(deposit.getPaymentMethod(),deposit.getAmount());
        }
        addList(depositHistoryList);

    }

    @Override
    public DepositHistory add(DepositHistory depositHistory) {
        return depositHistoryDao.save(depositHistory);
    }

    public List<DepositHistory> addList(List<DepositHistory> depositHistoryList) {
        return depositHistoryDao.saveAll(depositHistoryList);
    }

    @Override
    public List<DepositHistory> getByDate(Date date) {
        return depositHistoryDao.findByDate(date);
    }

    @Override
    public List<DepositHistory> getByRange(Date date1, Date date2) {
        return depositHistoryDao.findByDateGreaterThanEqualAndDateLessThanEqualOrderById(date1, date2);
    }

    @Override
    public List<DepositHistory> getByRangeIsp(PaymentMethod method, Date date1, Date date2) {
        return depositHistoryDao.findByPaymentMethodAndDateGreaterThanEqualAndDateLessThanEqualOrderById(method, date1, date2);
    }
}
