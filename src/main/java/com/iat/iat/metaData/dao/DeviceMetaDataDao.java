package com.iat.iat.metaData.dao;

import com.iat.iat.metaData.model.DeviceMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceMetaDataDao extends JpaRepository<DeviceMetaData, Integer> {

    List<DeviceMetaData> findByUserId(int userId);

}
