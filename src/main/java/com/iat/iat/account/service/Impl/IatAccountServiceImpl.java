package com.iat.iat.account.service.Impl;

import com.iat.iat.account.dao.IatAccountDao;
import com.iat.iat.account.model.IatAccount;
import com.iat.iat.account.service.IatAccountService;
import com.iat.iat.exceptions.InvalidValuesException;
import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.payment.model.IatPackage;
import com.iat.iat.security.MyUserDetailsService;
import com.iat.iat.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class IatAccountServiceImpl implements IatAccountService {
    @Autowired IatAccountDao iatAccountDao;
    @Autowired MyUserDetailsService myUserDetailsService;
    @Override
    public IatAccount addAccount(IatAccount iatAccount) {
        return iatAccountDao.save(iatAccount);
    }

    @Override
    @Transactional
    public IatAccount updateAccount(IatAccount iatAccount, IatPackage iatPackage) {
        boolean bool =existsByContact(iatAccount.getContact());
        if(bool){
            IatAccount iatAccount1= getAccount(iatAccount.getContact());
            iatAccount1.setLastTransaction(iatAccount.getLastTransaction());
            if(iatAccount1.getExpireAt().before(new Date())){
                iatAccount1.setExpireAt(iatAccount.getExpireAt());
                return iatAccountDao.save(iatAccount1);
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(iatAccount1.getExpireAt());
            iatAccount1.setExpireAt(getNewExpiry(cal, iatPackage));

            return iatAccountDao.save(iatAccount1);
        }
       return  addAccount(iatAccount);
    }

    private Date getNewExpiry(Calendar cal, IatPackage iatPackage) {
        if(iatPackage.equals(IatPackage.DAILY)){
            cal.add(Calendar.DAY_OF_MONTH,1);
            return cal.getTime();

        }else if(iatPackage.equals(IatPackage.WEEKLY)){
            cal.add(Calendar.WEEK_OF_MONTH,1);
            return cal.getTime();
        }else if(iatPackage.equals(IatPackage.MONTHLY)){
            cal.add(Calendar.MONTH,1);
            return cal.getTime();
        }else if(iatPackage.equals(IatPackage.YEARLY)){
            cal.add(Calendar.YEAR, 1);
            return cal.getTime();
        }

        throw new InvalidValuesException("invalid option");
    }


    @Override
    public IatAccount getAccount(String contact) {
        IatAccount iatAccount =iatAccountDao.findByContact(contact);
        if(iatAccount==null){
            throw new ResourceNotFoundException("no iat for contact yet");
        }
        return iatAccount;
    }

    @Override
    public IatAccount myIatAccount() {
        User user=myUserDetailsService.currentUser();
        return getAccount(user.getContact());
    }

    @Override
    public boolean existsByContact(String contact) {
        return iatAccountDao.existsByContact(contact);
    }
}
