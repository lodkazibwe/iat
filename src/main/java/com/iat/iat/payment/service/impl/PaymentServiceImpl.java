package com.iat.iat.payment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.service.DepositService;
import com.iat.iat.exceptions.InvalidValuesException;
import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.flutterWave.FlutterResp;
import com.iat.iat.flutterWave.FlutterWaveService;
import com.iat.iat.flutterWave.VerifyRespFW;
import com.iat.iat.payment.converter.PaymentConverter;
import com.iat.iat.payment.dao.PaymentDao;
import com.iat.iat.payment.dto.PaymentDto;
import com.iat.iat.payment.model.IatPackage;
import com.iat.iat.payment.model.Payment;
import com.iat.iat.payment.model.PaymentMethod;
import com.iat.iat.payment.service.PaymentService;
import com.iat.iat.security.MyUserDetailsService;
import com.iat.iat.user.model.User;
import com.iat.iat.wallet.model.Wallet;
import com.iat.iat.wallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    PaymentConverter paymentConverter;
    @Autowired MyUserDetailsService myUserDetailsService;
    @Autowired WalletService walletService;
    @Autowired DepositService depositService;
    @Autowired FlutterWaveService flutterWaveService;
    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Override
    public FlutterResp depositFW(double amount) {

        logger.info("Initiating payment...");

            logger.info(" payment gen...");
            Payment payment =new Payment();
            logger.info("getting current user...");
            User user=myUserDetailsService.currentUser();
            logger.info("getting  user wallet...");
            Wallet wallet =walletService.getByUser(user.getId());
            logger.info("setting payment wallet...");
            payment.setWalletId(wallet.getId());
            payment.setPaymentMethod(PaymentMethod.FLUTTER_WAVE);
            payment.setAmount(amount);
            payment.setStatus("pending");
            payment.setCreationDateTime(new Date());
            payment.setPaymentDate(new Date());
            payment.setExternalId("1");
            logger.info("saving payment...");
            paymentDao.save(payment);
            return initiateFW(amount, payment.getId(), user);



    }

    @Override
    @Transactional
    public Payment verifyFw(int tx_ref, String transactionId, String status) throws JsonProcessingException {
        if(status.equals("successful")){

            VerifyRespFW verifyRespFW =flutterWaveService.verify(transactionId);
            Payment payment=getById(tx_ref);
            logger.info(""+verifyRespFW.getData().getAmount());
            logger.info(""+verifyRespFW.getStatus());

            if(verifyRespFW.getData().getFlw_ref().equals(payment.getRef())){
                logger.info("double verification...");
                throw new InvalidValuesException("transaction already saved!");
            }

            if(verifyRespFW.getStatus().equals("success")
                    & verifyRespFW.getData().getAmount()>=payment.getAmount()){
                payment.setExternalId(transactionId);
                payment.setStatus(verifyRespFW.getStatus());
                payment.setMessage(verifyRespFW.getMessage());
                payment.setPaymentType(verifyRespFW.getData().getPayment_type());
                payment.setRef(verifyRespFW.getData().getFlw_ref());

                logger.info("updating wallet...");
                walletService.updateWallet(payment);
                logger.info("updating deposits...");
                depositService.updateDeposit(new Deposit(1, PaymentMethod.FLUTTER_WAVE, payment.getAmount()));
                return paymentDao.save(payment);

            }else{
                payment.setExternalId(transactionId);
                payment.setStatus(verifyRespFW.getStatus());
                payment.setMessage(verifyRespFW.getMessage());
                payment.setPaymentType(verifyRespFW.getData().getPayment_type());
                payment.setRef(verifyRespFW.getData().getFlw_ref());
                return paymentDao.save(payment);

            }



        }else{
            Payment payment=getById(tx_ref);
            payment.setExternalId(transactionId);
            payment.setStatus(status);
            return paymentDao.save(payment);
        }
    }

    @Override
    public Payment getById(int id) {
        return paymentDao.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No such payment With ID: " +id));
    }

    @Override
    public List<Payment> getByDate(Date paymentDate) {
        return paymentDao.findByPaymentDate(paymentDate);
    }

    @Override
    public List<Payment> myDatePayments(Date paymentDate) {
        Wallet wallet =walletService.myWallet();
        return paymentDao.findByPaymentDateAndWalletId(paymentDate, wallet.getId());
    }


    @Override
    public List<Payment> getByWallet(int walletId) {
        return paymentDao.findByWalletId(walletId);
    }


    @Override
    public Payment updatePayment(PaymentDto paymentDto) {
        return null;
    }

    @Override
    public void deletePayment(int id) {

    }

    private FlutterResp initiateFW(double amount, int paymentId, User user){
        return flutterWaveService.initiate(amount, paymentId, user);
    }

    @Override
    public Payment myLastPayment() {
        logger.info("getting current user...");
        User user=myUserDetailsService.currentUser();
        logger.info("getting  user wallet...");
        Wallet wallet =walletService.getByUser(user.getId());
        logger.info("getting  payment...");
        return getById(wallet.getLastPayment());
    }

    @Override
    public Payment buyIat(IatPackage iatPackage) {
        return null;
    }
}
