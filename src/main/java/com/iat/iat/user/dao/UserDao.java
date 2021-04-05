package com.iat.iat.user.dao;

import com.iat.iat.payment.model.Payment;
import com.iat.iat.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByContact(String contact);
    boolean existsByContact(String contact);
    //List<User> findFirst10OrderById(int id);
}
