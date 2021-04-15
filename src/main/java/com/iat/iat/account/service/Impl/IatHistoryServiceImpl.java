package com.iat.iat.account.service.Impl;

import com.iat.iat.account.dao.IatHistoryDao;
import com.iat.iat.account.model.Iat;
import com.iat.iat.account.model.IatHistory;
import com.iat.iat.account.service.IatHistoryService;
import com.iat.iat.account.service.IatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IatHistoryServiceImpl implements IatHistoryService {
    @Autowired IatHistoryDao iatHistoryDao;
    @Autowired IatService iatService;


    @Transactional
    @Scheduled(cron = "0 50 23 * * *",zone = "EAT")
    public void getCurrentIat(){
        List<Iat> iatList =iatService.getAll();
        List<IatHistory> iatHistoryList =new ArrayList<>();
        for(Iat iat:iatList){
            IatHistory iatHistory =new IatHistory();
            iatHistory.setAmount(iat.getAmount());
            iatHistory.setDate(new Date());
            iatHistory.setIspId(iat.getIsp());
            iatHistory.setIspName(iat.getIspName());
            iatHistoryList.add(iatHistory);
            iatService.reset(iat.getIsp(), iat.getAmount());
        }
        addList(iatHistoryList);

    }

    @Override
    public IatHistory add(IatHistory iatHistory) {
        return iatHistoryDao.save(iatHistory);

    }
    public List<IatHistory> addList(List<IatHistory> iatHistoryList) {
        return iatHistoryDao.saveAll(iatHistoryList);

    }

    @Override
    public List<IatHistory> getByDate(Date date) {
        return iatHistoryDao.findByDate(date);

    }

    @Override
    public List<IatHistory> getByRange(Date date1, Date date2) {
        return iatHistoryDao.findByDateGreaterThanEqualAndDateLessThanEqualOrderById(date1, date2);

    }

    @Override
    public List<IatHistory> getByRangeIsp(String isp, Date date1, Date date2) {
        return iatHistoryDao.findByIspNameAndDateGreaterThanEqualAndDateLessThanEqualOrderById(isp, date1, date2);

    }

}
