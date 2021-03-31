package com.iat.iat.isp.service;

import com.iat.iat.isp.dto.ISPDto;
import com.iat.iat.isp.model.ISP;

import java.util.List;

public interface ISPService {
    ISP addIsp(ISPDto ispDto);
    ISP getIsp(int id);
    List<ISP> getAll();
    ISP updateIsp(ISPDto ispDto);
    void deleteIsp(int id);
}
