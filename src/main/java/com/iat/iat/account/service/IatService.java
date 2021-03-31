package com.iat.iat.account.service;

import com.iat.iat.account.model.Deposit;

public interface IatService {
    Deposit addIatAccount(Deposit deposit);
    Deposit updateIat(Deposit deposit);
    Deposit getIatAccount(int id);
}
