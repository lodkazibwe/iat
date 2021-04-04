package com.iat.iat.account.service.Impl;

import com.iat.iat.account.dao.IatDao;
import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.model.Iat;
import com.iat.iat.account.service.IatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IatServiceImpl implements IatService {
    @Autowired IatDao iatDao;
    @Override
    public Iat addIatAccount(Iat iat1) {
        Iat iat =findByIsp(iat1.getIsp());
        iat.setAmount(iat.getAmount()+iat1.getAmount());
        return iatDao.save(iat);
    }

    @Override
    public Iat updateIat(Iat iat) {
        return null;
    }

    @Override
    public Iat getIatAccount(int id) {
        return null;
    }

    @Override
    public Iat findByIsp(int isp) {
        return iatDao.findByIsp(isp);
    }
}
