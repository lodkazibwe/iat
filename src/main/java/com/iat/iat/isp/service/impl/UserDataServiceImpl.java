package com.iat.iat.isp.service.impl;

import com.iat.iat.exceptions.InvalidValuesException;
import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.isp.converter.UserDataConverter;
import com.iat.iat.isp.dao.UserDataDao;
import com.iat.iat.isp.dto.ContactStatusDto;
import com.iat.iat.isp.dto.UserDataDto;
import com.iat.iat.isp.model.UserData;
import com.iat.iat.isp.service.UserDataService;
import com.iat.iat.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserDataServiceImpl implements UserDataService {
   @Autowired
   UserDataDao userDataDao;
   @Autowired UserDataConverter userDataConverter;
   @Autowired
   UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserDataServiceImpl.class);

    @Override
    public UserData addUserData(UserDataDto userDataDto) {
        logger.info("checking...");
        boolean bool =userDataDao.existsByContact(userDataDto.getContact());
        if(bool){
            logger.info("invalid...");
            throw new InvalidValuesException("use rData exists");
        }
        logger.info("converting...");
        UserData userData  =userDataConverter.dtoToEntity(userDataDto);
        return userDataDao.save(userData);
    }

    @Override
    public UserData getUserData(int id) {
        return userDataDao.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("No such user With ID: " +id));
    }

    @Override
    public List<UserData> getAll() {
        return userDataDao.findAll();
    }

    @Override
    public UserData getUserData(String contact) {
        return userDataDao.findByContact(contact);
    }

    @Override
    public List<UserData> getData(String residence) {
        return userDataDao.findByResidence(residence);
    }

    @Override
    public ContactStatusDto isContactAvailable(String contact) {
        boolean bool =userService.userExists(contact);
        if(bool){
            return new ContactStatusDto(contact,"Account Exists");
        }else{
            boolean bool1 =userDataDao.existsByContact(contact);
            if(bool1){
                return new ContactStatusDto(contact,"Available");
            }
            throw new ResourceNotFoundException("Invalid contact");
        }

    }

    @Override
    public boolean existsByContact(String contact) {
        return userDataDao.existsByContact(contact);
    }
}
