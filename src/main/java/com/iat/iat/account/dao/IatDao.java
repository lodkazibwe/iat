package com.iat.iat.account.dao;

import com.iat.iat.account.model.Iat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IatDao extends JpaRepository<Iat, Integer> {

    Iat findByIsp(int isp);

    boolean existsByIsp(int isp);

}
