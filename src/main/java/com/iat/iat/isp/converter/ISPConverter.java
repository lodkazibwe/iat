package com.iat.iat.isp.converter;

import com.iat.iat.isp.dto.ISPDto;
import com.iat.iat.isp.model.ISP;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ISPConverter {
    public ISPDto entityToDto(ISP isp){
        ISPDto ispDto =new ISPDto();
        ispDto.setId(isp.getId());
        ispDto.setName(isp.getName());
        ispDto.setUrl(isp.getUrl());
        return ispDto;
    }

    public ISP dtoToEntity(ISPDto ispDto){
        ISP isp =new ISP();
        isp.setName(ispDto.getName());
        isp.setUrl(ispDto.getUrl());
        return  isp;
    }

    public List<ISPDto> entityToDto(List<ISP> isps){
        return isps.stream().map(this::entityToDto).collect(Collectors.toList());
    }
    
    public List<ISP> dtoToEntity(List<ISPDto> ispDtos){
        return ispDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
