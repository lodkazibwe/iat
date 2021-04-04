package com.iat.iat.isp.service.impl;

import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.isp.converter.ISPConverter;
import com.iat.iat.isp.dao.ISPDao;
import com.iat.iat.isp.dto.ISPDto;
import com.iat.iat.isp.model.ISP;
import com.iat.iat.isp.service.ISPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISPServiceImpl implements ISPService {
    @Autowired ISPDao ispDao;
    @Autowired ISPConverter ispConverter;
    @Override
    public ISP addIsp(ISPDto ispDto) {
        ISP isp =ispConverter.dtoToEntity(ispDto);
        return ispDao.save(isp);
    }

    @Override
    public ISP getIsp(int id) {
        return ispDao.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No such isp With ID: " +id));
    }

    @Override
    public List<ISP> getAll() {
        return ispDao.findAll();
    }

    @Override
    public ISP updateIsp(ISPDto ispDto) {
        return null;
    }

    @Override
    public void deleteIsp(int id) {

    }
}
