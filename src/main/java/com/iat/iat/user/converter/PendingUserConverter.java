package com.iat.iat.user.converter;

import com.iat.iat.user.dto.PendingUserDto;
import com.iat.iat.user.model.PendingUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PendingUserConverter {
    public PendingUserDto entityToDto(PendingUser pendingUser){
        PendingUserDto pendingUserDto =new PendingUserDto();
        pendingUserDto.setExpiryDate(pendingUser.getExpiryDate());
        pendingUserDto.setId(pendingUser.getId());
        pendingUserDto.setNumber(pendingUser.getNumber());
        pendingUserDto.setStatus(pendingUser.getStatus());
        pendingUserDto.setToken(pendingUser.getToken());
        return pendingUserDto;

    }

    public PendingUser dtoToEntity(PendingUserDto pendingUserDto){
        PendingUser pendingUser =new PendingUser();
        pendingUser.setExpiryDate(pendingUserDto.getExpiryDate());
        pendingUser.setNumber(pendingUserDto.getNumber());
        pendingUser.setStatus(pendingUserDto.getStatus());
        pendingUser.setToken(pendingUserDto.getToken());
        return pendingUser;

    }

    public List<PendingUserDto> entityToDto(List<PendingUser> pendingUsers){
        return  pendingUsers.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<PendingUser> dtoToEntity(List<PendingUserDto> pendingUsers){
        return  pendingUsers.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
