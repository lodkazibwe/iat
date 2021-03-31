package com.iat.iat.user.dao;

import com.iat.iat.user.model.CodeStatus;
import com.iat.iat.user.model.PendingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PendingUserDao extends JpaRepository<PendingUser, Integer> {
    boolean existsByNumber(String contact);
    boolean existsByNumberAndStatusAndTokenAndExpiryDateLessThan(
            String number, CodeStatus status, String token, Date expiryDate);

    PendingUser findByNumber(String contact);

}
