package com.iat.iat.security;

import com.iat.iat.user.dao.UserDao;
import com.iat.iat.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user =getUser(phone);
        MyUserDetails userDetails=new MyUserDetails(user);
        if(user !=null){
            return userDetails;

        }else{
            throw new UsernameNotFoundException("User not exist with name : " + phone);
        }
    }

    public User getUser(String phone){
        return userDao.findByContact(phone);
    }


    public User currentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName=auth.getName();
        return  getUser(userName);
    }
}
