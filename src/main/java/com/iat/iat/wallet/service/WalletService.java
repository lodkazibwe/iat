package com.iat.iat.wallet.service;

import com.iat.iat.payment.dto.PaymentDto;
import com.iat.iat.payment.model.Payment;
import com.iat.iat.wallet.dto.WalletDto;
import com.iat.iat.wallet.model.Wallet;

import java.util.List;

public interface WalletService {
    Wallet create(WalletDto walletDto);
    Wallet myWallet();
    Wallet getById(int id);
    Wallet getByUser(int userId);
    Wallet getByContact(String contact);
    List<Wallet> getByBalance(double balance);
    List<Wallet> getAll();
    List<Wallet> getByBalanceLess(double balance);
    List<Wallet> getByBalanceGreater(double balance);
    Wallet updateWallet(WalletDto walletDto);
    Wallet updateWallet(Payment payment);
    Wallet updateWallet(PaymentDto paymentDto);


    Void deleteWallet(int id);
}
