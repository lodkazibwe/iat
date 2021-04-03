package com.iat.iat.wallet.service.impl;

import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.payment.dto.PaymentDto;
import com.iat.iat.payment.model.Payment;
import com.iat.iat.security.MyUserDetailsService;
import com.iat.iat.user.model.User;
import com.iat.iat.wallet.converter.WalletConverter;
import com.iat.iat.wallet.dao.WalletDao;
import com.iat.iat.wallet.dto.WalletDto;
import com.iat.iat.wallet.model.Wallet;
import com.iat.iat.wallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired WalletDao walletDao;
    @Autowired WalletConverter walletConverter;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    private final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

    @Override
    public Wallet create(WalletDto walletDto) {
        logger.info("converting...");
        Wallet wallet =walletConverter.dtoToEntity(walletDto);
        logger.info("saving...");
        return walletDao.save(wallet);
    }

    @Override
    public Wallet getById(int id) {

        return walletDao.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No such wallet With ID: " +id));
    }

    @Override
    public Wallet getByUser(int userId) {

        return walletDao.findByUid(userId);
    }

    @Override
    public Wallet myWallet() {
        logger.info("getting user");
        User user =myUserDetailsService.currentUser();
        return getByUser(user.getId());
    }

    @Override
    public List<Wallet> getByBalance(double balance) {

        return walletDao.findByBalance(balance);
    }

    @Override
    public List<Wallet> getByBalanceLess(double balance) {

        return walletDao.findByBalanceLessThan(balance);
    }

    @Override
    public List<Wallet> getByBalanceGreater(double balance) {

        return walletDao.findByBalanceGreaterThan(balance);
    }


    @Override
    public Wallet updateWallet(WalletDto walletDto) {
        logger.info("getting wallet...");
        Wallet wallet =getById(walletDto.getId());
        logger.info("updating wallet amount...");
        wallet.setBalance(wallet.getBalance()+walletDto.getBalance());
        logger.info("saving...");
        return walletDao.save(wallet);
    }

    @Override
    public Wallet updateWallet(Payment payment) {
        logger.info("getting wallet...");
        Wallet wallet =getById(payment.getWalletId());
        logger.info("updating wallet amount...");
        wallet.setBalance(wallet.getBalance()+payment.getAmount());
        logger.info("updating wallet last payment...");
        wallet.setLastPayment(payment.getId());
        logger.info("saving...");
        return walletDao.save(wallet);
    }

    @Override
    public Wallet updateWallet(PaymentDto paymentDto) {
        logger.info("getting wallet...");
        Wallet wallet =getById(paymentDto.getWalletId());
        logger.info("updating wallet amount...");
        wallet.setBalance(wallet.getBalance()+paymentDto.getAmount());
        logger.info("saving...");
        return walletDao.save(wallet);
    }

    @Override
    public Wallet getByContact(String contact) {
        return walletDao.findByContact(contact);
    }

    @Override
    public Void deleteWallet(int id) {
        return null;
    }
}
