package com.iat.iat.user.dao;

import com.iat.iat.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByContact(String contact);
    boolean existsByContact(String contact);
}
