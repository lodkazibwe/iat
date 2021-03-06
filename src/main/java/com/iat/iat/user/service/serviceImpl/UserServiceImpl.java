package com.iat.iat.user.service.serviceImpl;

import com.iat.iat.exceptions.InvalidValuesException;
import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.isp.model.UserData;
import com.iat.iat.isp.service.UserDataService;
import com.iat.iat.security.AuthRequest;
import com.iat.iat.security.AuthResponse;
import com.iat.iat.security.AuthService;
import com.iat.iat.security.MyUserDetailsService;
import com.iat.iat.user.converter.UserConverter;
import com.iat.iat.user.dao.UserDao;
import com.iat.iat.user.dto.ChangePassDto;
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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired UserConverter userConverter;
    @Autowired UserDao userDao;
    @Autowired WalletService walletService;
    @Autowired PendingUserService pendingUserService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired AuthService authService;
    @Autowired UserDataService userDataService;
    @Autowired
    MyUserDetailsService myUserDetailsService;
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
    @Transactional
    public AuthResponse addUser(UserDto createUserDto, HttpServletRequest request) {
        logger.info("checking user data...");
        //boolean bool1 =pendingUserService.isVerified(createUserDto.getContact());
        //boolean bool2 =userDataService.existsByContact(createUserDto.getContact());

        logger.info("checking user...");
        boolean bool =userExists(createUserDto.getContact());
        if(bool){
            logger.error("contact already used...");
            throw new ResourceNotFoundException("user already exists...");
        }
        logger.info("generating user...");
        UserData userData =userDataService.getUserData(createUserDto.getContact());
        User user =userConverter.dtoToEntity(createUserDto);
        logger.info("setting user role...");
        user.setRoles(getRoles());
        user.setName(userData.getFirstName()+" "+userData.getLastName());
        user.setEmail(userData.getEmail());
        logger.info("saving user and creating wallet...");
        createWallet(userDao.save(user));
        AuthRequest authRequest =new AuthRequest();
        logger.info("generating auth key...");
        authRequest.setUserName(createUserDto.getContact());
        authRequest.setPassword(createUserDto.getPassWord());
        return new AuthResponse(authService.getJwt(authRequest, request));
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
        User user =userDao.findByContact(contact);
        if(user==null){
            throw new ResourceNotFoundException("No such user: " +contact);
        }
        return user;
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
    @Transactional
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


    @Override
    public void updateUserImage(String fileName, int uid) {
        User user=getUser(uid);
        user.setImage(fileName);
        userDao.save(user);
    }

    @Override
    public User changePassword(ChangePassDto changePassDto) {
        User user= myUserDetailsService.currentUser();
        if(changePassDto.getNewPass().equals(changePassDto.getConfirm())){
            boolean bool =authService.checkUser(new AuthRequest(user.getContact(), changePassDto.getOldPass()));
            if(bool){
                user.setPassword(passwordEncoder.encode(changePassDto.getNewPass()));
                return userDao.save(user);
            }
            logger.info("");
            //passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(changePassDto.getOldPass()));
            throw new InvalidValuesException("incorrect old password");
        }
        throw new InvalidValuesException("password mismatch");

    }
}
