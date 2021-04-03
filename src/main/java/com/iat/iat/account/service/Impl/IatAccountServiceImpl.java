package com.iat.iat.account.service.Impl;

import com.iat.iat.account.dao.IatAccountDao;
import com.iat.iat.account.model.IatAccount;
import com.iat.iat.account.service.IatAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IatAccountServiceImpl implements IatAccountService {
    @Autowired IatAccountDao iatAccountDao;
    @Override
    public IatAccount addAccount(IatAccount iatAccount) {
        return iatAccountDao.save(iatAccount);
    }

    @Override
    public IatAccount updateAccount(IatAccount iatAccount) {
        boolean bool =existsByContact(iatAccount.getContact());
        if(bool){
            IatAccount iatAccount1= getAccount(iatAccount.getContact());
            iatAccount1.setLastTransaction(iatAccount.getLastTransaction());
            iatAccount1.setExpireAt(iatAccount.getExpireAt());
            return iatAccountDao.save(iatAccount1);
        }
       return  addAccount(iatAccount);
    }

    @Override
    public IatAccount getAccount(String contact) {
        return iatAccountDao.findByContact(contact);
    }

    @Override
    public boolean existsByContact(String contact) {
        return iatAccountDao.existsByContact(contact);
    }
}
