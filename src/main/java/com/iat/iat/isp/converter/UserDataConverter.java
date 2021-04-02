package com.iat.iat.isp.converter;

import com.iat.iat.isp.dto.UserDataDto;
import com.iat.iat.isp.model.UserData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataConverter {
    public UserDataDto entityToDto(UserData userData){
        UserDataDto userDataDto =new UserDataDto();
        userDataDto.setId(userData.getId());
        userDataDto.setContact(userData.getContact());
        userDataDto.setEmail(userData.getEmail());
        userDataDto.setFirstName(userData.getFirstName());
        userDataDto.setJob(userData.getJob());
        userDataDto.setLastName(userData.getLastName());
        userDataDto.setNin(userData.getNin());
        userDataDto.setResidence(userData.getResidence());
        return userDataDto;
    }

    public UserData dtoToEntity(UserDataDto userDataDto){
        UserData userData =new UserData();
        userData.setContact(userDataDto.getContact());
        userData.setEmail(userDataDto.getEmail());
        userData.setFirstName(userDataDto.getFirstName());
        userData.setResidence(userDataDto.getResidence());
        userData.setJob(userDataDto.getJob());
        userData.setLastName(userDataDto.getLastName());
        userData.setNin(userDataDto.getNin());
        return userData;
    }

    public List<UserDataDto> entityToDto(List<UserData> userDataList){
        return userDataList.stream().map(this::entityToDto).collect(Collectors.toList());

    }
    public List<UserData> dtoToEntity(List<UserDataDto> userDataList){
        return userDataList.stream().map(this::dtoToEntity).collect(Collectors.toList());

    }

}
