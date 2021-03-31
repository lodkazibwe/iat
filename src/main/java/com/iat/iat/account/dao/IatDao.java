package com.iat.iat.account.dao;

import com.iat.iat.account.model.Iat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IatDao extends JpaRepository<Iat, Integer> {

}
