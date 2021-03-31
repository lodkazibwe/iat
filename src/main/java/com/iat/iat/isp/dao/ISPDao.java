package com.iat.iat.isp.dao;

import com.iat.iat.isp.model.ISP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISPDao extends JpaRepository<ISP, Integer> {
}
