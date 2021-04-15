package com.iat.iat.account.service;

import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.model.Iat;

import java.util.List;

public interface IatService {
    Iat addIatAccount(Iat iat);
    Iat updateIat(Iat iat);
    Iat findByIsp(int isp);
    Iat getIatAccount(int id);
    List<Iat> getAll();
    void reset();
    void reset(int isp, double amount);


}
