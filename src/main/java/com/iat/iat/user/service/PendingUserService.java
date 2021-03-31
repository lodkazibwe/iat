package com.iat.iat.user.service;

import com.iat.iat.user.dto.PendingUserDto;
import com.iat.iat.user.model.PendingUser;

public interface PendingUserService {
    PendingUser addNew(String contact);
    PendingUser getUser(int id);
    PendingUser getUser(String contact);
    PendingUser updateUser(String contact);

    PendingUser verifyPassCode(PendingUserDto pendingUserDto);
    boolean isVerified(String contact);

}
