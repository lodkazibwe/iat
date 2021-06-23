package com.iat.iat.security;

import com.iat.iat.metaData.service.MetaDataService;
import com.iat.iat.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired MetaDataService metaDataService;
    private final Logger logger = LoggerFactory.getLogger(MySimpleUrlAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain chain,
                                        Authentication authentication) throws IOException, ServletException {

        logger.info("onAuthenticationSuccess");
        loginNotification(authentication, request);


    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("onAuthenticationSuccess 2");



    }

    private void loginNotification(Authentication authentication,
                                   HttpServletRequest request) {
        try {
            if (authentication.getPrincipal() instanceof User) {
                metaDataService.verifyDevice(((User)authentication.getPrincipal()), request);
            }
        } catch(Exception e) {
            logger.error("An error occurred verifying device or location");
            throw new RuntimeException(e);
        }
    }
}
