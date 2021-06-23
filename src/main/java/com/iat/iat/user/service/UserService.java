package com.iat.iat.user.service;

import com.iat.iat.security.AuthResponse;
import com.iat.iat.user.dto.ChangePassDto;
import com.iat.iat.user.dto.UserDto;
import com.iat.iat.user.model.PendingUser;
import com.iat.iat.user.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    PendingUser addPendingUser(String contact);
    AuthResponse addUser(UserDto userDto, HttpServletRequest request);
    User getUser(int id);
    User getUser(String contact);
    List<User> getUsers(String category);
    List<User> getAll();
    User updateUser(UserDto userDto);
    Void DeleteUser(int id);
    boolean userExists(String contact);
    User changePassword(ChangePassDto changePassDto);

    void updateUserImage(String fileName, int id);
}
