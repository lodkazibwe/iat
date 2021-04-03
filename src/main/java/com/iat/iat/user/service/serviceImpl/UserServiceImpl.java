package com.iat.iat.user.service.serviceImpl;

import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.user.converter.UserConverter;
import com.iat.iat.user.dao.UserDao;
import com.iat.iat.user.dto.UserDto;
import com.iat.iat.user.model.PendingUser;
import com.iat.iat.user.model.Role;
import com.iat.iat.user.model.User;
import com.iat.iat.user.service.PendingUserService;
import com.iat.iat.user.service.UserService;
import com.iat.iat.wallet.dto.WalletDto;
import com.iat.iat.wallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired UserConverter userConverter;
    @Autowired UserDao userDao;
    @Autowired WalletService walletService;
    @Autowired PendingUserService pendingUserService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public PendingUser addPendingUser(String contact) {
        logger.info("checking user...");
        boolean bool =userExists(contact);
        if(bool){
            logger.error("contact already used...");
            throw new ResourceNotFoundException("user already exists...");
        }
        return pendingUserService.addNew(contact);
    }



    @Override
    public User addUser(UserDto createUserDto) {
        logger.info("checking verification...");
        //boolean bool1 =pendingUserService.isVerified(createUserDto.getContact());
        logger.info("checking user...");
        boolean bool =userExists(createUserDto.getContact());
        if(bool){
            logger.error("contact already used...");
            throw new ResourceNotFoundException("user already exists...");
        }
        logger.info("generating user...");
        User user =userConverter.dtoToEntity(createUserDto);
        logger.info("setting user role...");
        user.setRoles(getRoles());
        logger.info("saving user and creating wallet...");
        createWallet(userDao.save(user));
        return user;

    }

    private void createWallet(User user) {
        WalletDto walletDto =new WalletDto();
        walletDto.setUid(user.getId());
        walletDto.setBalance(0);
        walletDto.setContact(user.getContact());
        logger.info("contacting wallet service...");
        walletService.create(walletDto);
    }

    public boolean userExists(String contact){
        return userDao.existsByContact(contact);
    }

    private Set<Role> getRoles(){
        logger.info("Role user...");
        Role role = new Role("USER");
        return new HashSet<>(Collections.singletonList(role));
    }

    @Override
    public User getUser(int id) {

        logger.info("getting user...");
        return userDao.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No such user With ID: " +id));
    }

    @Override
    public User getUser(String contact) {

        return userDao.findByContact(contact);
    }

    @Override
    public List<User> getUsers(String category) {
        logger.info("not codded...");
        return null;
    }

    @Override
    public List<User> getAll() {
        logger.info("getting all...");
        return userDao.findAll();
    }

    @Override
    public User updateUser(UserDto userDto) {
        logger.info("getting user by id...");
       User user =getUser(userDto.getId());
        logger.info("updating userName ...");
       user.setName(userDto.getName());
        return userDao.save(user);
    }

    @Override
    public Void DeleteUser(int id) {
        return null;
    }
}
