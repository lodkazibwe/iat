package com.iat.iat.security;

import com.iat.iat.exceptions.InvalidValuesException;
import com.iat.iat.metaData.service.MetaDataService;
import com.iat.iat.user.converter.UserConverter;


import com.iat.iat.user.dto.UserDto;

import com.iat.iat.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired UserConverter userConverter;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired MetaDataService metaDataService;
    public String getJwt(AuthRequest authRequest, HttpServletRequest request){
        try {
            logger.info("authenticating....");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception e) {
            logger.info("invalid user name or pass....");
            throw new InvalidValuesException("Incorrect name or password");

        }
        logger.info("getting user details....");
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUserName());
        logger.info("getting token....");
        logger.info("authenticated....");
        User user =myUserDetailsService.getUser(authRequest.getUserName());
        metaDataService.verifyDevice(user, request);
       return jwtUtil.generateToken(userDetails);


    }

    public boolean checkUser(AuthRequest authRequest){
        try {
            logger.info("checking auth....");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception e) {
            logger.info("invalid pass....");
            throw new InvalidValuesException("Incorrect old password");

        }
        return true;
    }

    public AuthResponseV2 authenticate(AuthRequest authRequest, HttpServletRequest request){
        String jwt =getJwt(authRequest, request);
        User user =myUserDetailsService.getUser(authRequest.getUserName());
        metaDataService.verifyDevice(user, request);
        return new AuthResponseV2(jwt, userConverter.entityToDto(user));

    }


   /* public void getMetaData(HttpServletRequest request, String userAgent, int id, String contact){
        DeviceMetaData deviceMetaData =new DeviceMetaData();
        deviceMetaData.setClientIp(extractIp(request));
        deviceMetaData.setDeviceDetails(getDeviceDetails(userAgent));
        deviceMetaData.setUserId(id);
        deviceMetaData.setContact(contact);
        deviceMetaData.setLastLoggedIn(new Date());

    }*/

}
