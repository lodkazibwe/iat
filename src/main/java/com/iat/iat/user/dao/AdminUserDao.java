package com.iat.iat.user.dao;

import com.iat.iat.user.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserDao extends JpaRepository<AdminUser, Integer> {
    boolean existsByContact(String contact);

    AdminUser findByContact(String contact);

    List<AdminUser> findByTittle(String tittle);

}
