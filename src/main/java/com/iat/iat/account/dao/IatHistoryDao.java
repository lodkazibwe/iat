package com.iat.iat.account.dao;


import com.iat.iat.account.model.IatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IatHistoryDao extends JpaRepository<IatHistory, Integer> {
    List<IatHistory> findByDate(Date date);

    List<IatHistory> findByDateGreaterThanEqualAndDateLessThanEqualOrderById(Date date1, Date date2);

    List<IatHistory> findByIspNameAndDateGreaterThanEqualAndDateLessThanEqualOrderById(String isp, Date date1, Date date2);
}
