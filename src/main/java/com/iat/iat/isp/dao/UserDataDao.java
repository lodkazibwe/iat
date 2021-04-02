package com.iat.iat.isp.dao;

import com.iat.iat.isp.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataDao extends JpaRepository<UserData, Integer> {
    boolean existsByContact(String contact);

    UserData findByContact(String contact);

    List<UserData> findByResidence(String residence);

}
