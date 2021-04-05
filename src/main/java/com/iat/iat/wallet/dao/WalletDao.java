package com.iat.iat.wallet.dao;

import com.iat.iat.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletDao extends JpaRepository<Wallet, Integer> {
    Wallet findByUid(int userId);

    List<Wallet> findByBalance(double balance);
    List<Wallet> findByBalanceLessThan(double balance);
    List<Wallet> findByBalanceGreaterThan(double balance);
    Wallet findByContact(String contact);
}
