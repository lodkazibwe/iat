package com.iat.iat.account.service;

import com.iat.iat.account.model.DepositHistory;
import com.iat.iat.account.model.IatHistory;
import com.iat.iat.payment.model.PaymentMethod;

import java.util.Date;
import java.util.List;

public interface DepositHistoryService {
    DepositHistory add(DepositHistory depositHistory);
    List<DepositHistory> addList(List<DepositHistory> depositHistoryList);
    List<DepositHistory> getByDate(Date date);
    List<DepositHistory> getByRange(Date date1, Date date2);
    List<DepositHistory> getByRangeIsp(PaymentMethod method, Date date1, Date date2);
}
