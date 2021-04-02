package com.iat.iat.user.service;

import com.iat.iat.user.dto.AdminUserDto;
import com.iat.iat.user.dto.UserDto;
import com.iat.iat.user.model.AdminUser;
import com.iat.iat.user.model.User;

import java.util.List;

public interface AdminService {
    AdminUser addAdmin(AdminUserDto adminUserDto);
    AdminUser addSuperUser();
    AdminUser getAdmin(int id);
    AdminUser getAdmin(String contact);
    List<AdminUser> getByCategory(String category);
    List<AdminUser> getByTittle(String tittle);
    List<AdminUser> getAll();
    AdminUser updateAdmin(AdminUserDto adminUserDto);
    Void DeleteAdmin(int id);
}
