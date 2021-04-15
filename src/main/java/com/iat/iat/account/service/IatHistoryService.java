package com.iat.iat.account.service;

import com.iat.iat.account.model.IatHistory;

import java.util.Date;
import java.util.List;

public interface IatHistoryService {
    IatHistory add(IatHistory iatHistory);
    List<IatHistory> addList(List<IatHistory> iatHistoryList);
    List<IatHistory> getByDate(Date date);
    List<IatHistory> getByRange(Date date1, Date date2);
    List<IatHistory> getByRangeIsp(String isp, Date date1, Date date2);
}
