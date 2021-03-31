package com.iat.iat.user.converter;

import com.iat.iat.user.dto.AdminUserDto;
import com.iat.iat.user.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminUserConverter {
    @Autowired
    CategoryConverter categoryConverter;
    public AdminUserDto entityToDto(AdminUser adminUser){
        AdminUserDto adminUserDto =new AdminUserDto();
        adminUserDto.setId(adminUser.getId());
        adminUserDto.setContact(adminUser.getContact());
        adminUserDto.setName(adminUser.getName());
        adminUserDto.setTittle(adminUser.getTittle());
        return adminUserDto;
    }

    public AdminUser dtoToEntity(AdminUserDto adminUserDto){
        AdminUser adminUser = new AdminUser();
        adminUser.setContact(adminUserDto.getContact());
        adminUser.setName(adminUserDto.getName());
        adminUser.setTittle(adminUserDto.getTittle());
        return adminUser;
    }

    public List<AdminUserDto> entityToDto(List<AdminUser> adminUsers){
        return adminUsers.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<AdminUser> dtoToEntity(List<AdminUserDto> adminUserDtos) {
        return adminUserDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
