package com.iat.iat.isp.service;

import com.iat.iat.isp.dto.ContactStatusDto;
import com.iat.iat.isp.dto.UserDataDto;
import com.iat.iat.isp.model.UserData;

import java.util.List;

public interface UserDataService {
    UserData addUserData(UserDataDto userDataDto);
    UserData getUserData(int id);
    UserData getUserData(String contact);
    List<UserData> getData(String residence);
    ContactStatusDto isContactAvailable(String contact);
    boolean existsByContact(String contact);

    List<UserData> getAll();
}
