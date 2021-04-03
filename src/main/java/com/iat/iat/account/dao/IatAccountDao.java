package com.iat.iat.account.dao;

import com.iat.iat.account.model.IatAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IatAccountDao extends JpaRepository<IatAccount, Integer> {

}
