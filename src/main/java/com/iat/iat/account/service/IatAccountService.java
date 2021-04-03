package com.iat.iat.account.service;

import com.iat.iat.account.model.IatAccount;

public interface IatAccountService {
    IatAccount addAccount(IatAccount iatAccount);
    IatAccount updateAccount(IatAccount iatAccount);
    IatAccount getAccount(String contact);
    boolean existsByContact(String contact);
}
