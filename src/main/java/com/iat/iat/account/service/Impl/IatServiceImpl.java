package com.iat.iat.account.service.Impl;

import com.iat.iat.account.dao.IatDao;
import com.iat.iat.account.model.Iat;
import com.iat.iat.account.service.IatService;
import com.iat.iat.exceptions.InvalidValuesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IatServiceImpl implements IatService {
    @Autowired IatDao iatDao;

    @Override
    @Transactional
    public Iat addIatAccount(Iat iat1) {
        Iat iat =new Iat();
        iat.setAmount(0);
        iat.setIsp(iat1.getIsp());
        return iatDao.save(iat);
    }

    @Override
    @Transactional
    public Iat updateIat(Iat iatUpdate) {
        boolean bool =existsByIsp(iatUpdate.getIsp());
        if(bool){
        Iat iat =findByIsp(iatUpdate.getIsp());
        iat.setAmount(iat.getAmount()+iatUpdate.getAmount());
        return iatDao.save(iat);
        }
        throw new InvalidValuesException("invalid Iat account");
    }

    @Override
    public Iat getIatAccount(int id) {
        return null;
    }

    @Override
    public Iat findByIsp(int isp) {
        return iatDao.findByIsp(isp);
    }

    public boolean existsByIsp(int isp){
        return iatDao.existsByIsp(isp);
    }

    @Override
    public List<Iat> getAll() {
        return iatDao.findAll();
    }
}
