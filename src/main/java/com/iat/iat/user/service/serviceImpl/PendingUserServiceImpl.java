package com.iat.iat.user.service.serviceImpl;

import com.iat.iat.exceptions.InvalidValuesException;
import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.user.dao.PendingUserDao;
import com.iat.iat.user.dto.PendingUserDto;
import com.iat.iat.user.model.CodeStatus;
import com.iat.iat.user.model.PendingUser;
import com.iat.iat.user.service.PendingUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class PendingUserServiceImpl implements PendingUserService {

    @Autowired PendingUserDao pendingUserDao;
    //@Autowired UserService1 userService;
    private final Logger logger = LoggerFactory.getLogger(PendingUserServiceImpl.class);
    @Override
    public PendingUser addNew(String contact) {
        logger.info("checking user...");
        boolean bool =userExists(contact);
        if(bool){
            updateUser(contact);
        }

        logger.info("generating user...");
        PendingUser pendingUser =new PendingUser();
        pendingUser.setNumber(contact);
        return createPendingUser(pendingUser);

    }
    public boolean userExists(String contact){
        return pendingUserDao.existsByNumber(contact);
    }

    @Override
    public PendingUser getUser(int id) {
        return pendingUserDao.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("No such user With ID: " +id));
    }

    @Override
    public PendingUser getUser(String contact) {
        return pendingUserDao.findByNumber(contact);
    }

    @Override
    public PendingUser updateUser(String contact) {
        logger.info("getting user...");
        PendingUser pendingUser=getUser(contact);
        return createPendingUser(pendingUser);

    }

    private PendingUser createPendingUser(PendingUser pendingUser) {
        logger.info("setting expiry date...");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        pendingUser.setExpiryDate(calendar.getTime());
        logger.info("generating passcode...");
        Random random = new Random();
        String rand = String.format("%04d", random.nextInt(10000));
        String pin ="W-"+1111;
        pendingUser.setToken(pin);
        sendSms(pendingUser.getNumber(), pin);
        pendingUser.setStatus(CodeStatus.SENT);
        return pendingUserDao.save(pendingUser);
    }

    private void sendSms(String number, String pin) {
        logger.info("contact sms service ...");
        logger.info(number+": "+pin);
    }

    @Override
    public PendingUser verifyPassCode(PendingUserDto pendingUserDto) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);

        PendingUser pendingUser=getUser(pendingUserDto.getNumber());
        if(pendingUser.getToken().equals(pendingUserDto.getToken())
                & (calendar.getTime().before(pendingUser.getExpiryDate()))){
            pendingUser.setStatus(CodeStatus.VERIFIED);
            return pendingUserDao.save(pendingUser);

        }
        throw new InvalidValuesException("Invalid token or Contact");
    }

    @Override
    public boolean isVerified(String contact) {
        PendingUser pendingUser =getUser(contact);
        return pendingUser.getStatus().equals(CodeStatus.VERIFIED);
    }
}
