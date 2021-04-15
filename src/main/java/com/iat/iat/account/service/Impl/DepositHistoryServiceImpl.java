package com.iat.iat.account.service.Impl;

import com.iat.iat.account.dao.DepositHistoryDao;
import com.iat.iat.account.model.DepositHistory;
import com.iat.iat.account.service.DepositHistoryService;
import com.iat.iat.payment.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepositHistoryServiceImpl implements DepositHistoryService {
    @Autowired DepositHistoryDao depositHistoryDao;
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
