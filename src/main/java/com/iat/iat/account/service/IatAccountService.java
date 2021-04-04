package com.iat.iat.account.service;

import com.iat.iat.account.model.IatAccount;
import com.iat.iat.payment.model.IatPackage;

public interface IatAccountService {
    IatAccount addAccount(IatAccount iatAccount);
    IatAccount updateAccount(IatAccount iatAccount, IatPackage iatPackage);
    IatAccount getAccount(String contact);
    boolean existsByContact(String contact);
}
