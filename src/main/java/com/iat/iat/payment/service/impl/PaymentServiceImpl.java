package com.iat.iat.payment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.model.Iat;
import com.iat.iat.account.model.IatAccount;
import com.iat.iat.account.service.DepositService;
import com.iat.iat.account.service.IatAccountService;
import com.iat.iat.account.service.IatService;
import com.iat.iat.exceptions.InvalidValuesException;
import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.flutterWave.FlutterResp;
import com.iat.iat.flutterWave.FlutterWaveService;
import com.iat.iat.flutterWave.VerifyRespFW;
import com.iat.iat.isp.service.UserDataService;
import com.iat.iat.payment.converter.PaymentConverter;
import com.iat.iat.payment.dao.PaymentDao;
import com.iat.iat.payment.dto.BuyIatDto;
import com.iat.iat.payment.dto.PaymentDto;
import com.iat.iat.payment.model.IatPackage;
import com.iat.iat.payment.model.Payment;
import com.iat.iat.payment.model.PaymentMethod;
import com.iat.iat.payment.service.PaymentService;
import com.iat.iat.security.MyUserDetailsService;
import com.iat.iat.transaction.dto.TransactionDto;
import com.iat.iat.transaction.model.Transaction;
import com.iat.iat.transaction.model.TransactionStatus;
import com.iat.iat.transaction.service.TransactionService;
import com.iat.iat.user.model.User;
import com.iat.iat.wallet.model.Wallet;
import com.iat.iat.wallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
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
    @Autowired UserDataService userDataService;
    @Autowired IatService iatService;
    @Autowired IatAccountService iatAccountService;
    @Autowired TransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Override
    @Transactional
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
    public String verifyFw(int tx_ref, String transactionId, String status) throws JsonProcessingException {
        if(status.equals("successful")){

            VerifyRespFW verifyRespFW =flutterWaveService.verify(transactionId);
            Payment payment=getById(verifyRespFW.getData().getTx_ref());
            logger.info(""+verifyRespFW.getData().getAmount());
            logger.info(""+verifyRespFW.getStatus());
            //verifyRespFW.getData().getTx_ref();

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
               // return
                        paymentDao.save(payment);
                        return "payment successful";

            }else{
                payment.setExternalId(transactionId);
                payment.setStatus(verifyRespFW.getStatus());
                payment.setMessage(verifyRespFW.getMessage());
                payment.setPaymentType(verifyRespFW.getData().getPayment_type());
                payment.setRef(verifyRespFW.getData().getFlw_ref());
                //return
                        paymentDao.save(payment);
                        return "payment "+verifyRespFW.getStatus();

            }



        }else{
            Payment payment=getById(tx_ref);
            payment.setExternalId(transactionId);
            payment.setStatus(status);
            //return
                    paymentDao.save(payment);
                    return "payment "+status;
        }
    }

    @Override
    public Payment getById(int id) {
        return paymentDao.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No such payment With ID: " +id));
    }

    @Override
    public List<Payment> getByDate(Date paymentDate) {

        return paymentDao.findByPaymentDateAndStatus(paymentDate, "success");
    }

    @Override
    public List<Payment> getByDateRange(Date date1, Date date2) {
        return paymentDao.findByPaymentDateGreaterThanEqualAndPaymentDateLessThanEqualAndStatus(date1, date2, "success");
    }

    @Override
    public List<Payment> myDatePayments(Date paymentDate) {
        Wallet wallet =walletService.myWallet();
        return paymentDao.findByPaymentDateAndWalletId(paymentDate, wallet.getId());
    }


    @Override
    public List<Payment> allByWallet(int walletId) {
        return paymentDao.findByWalletIdOrderByIdDesc(walletId);
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
    @Transactional
    public Payment buyIat(BuyIatDto buyIatDto) {
        //boolean bool =userDataService.existsByContact(contact);
        //if(bool){ User user =myUserDetailsService.currentUser(); }
        //throw new InvalidValuesException("invalid contact");


        logger.info("getting current user...");
        User user =myUserDetailsService.currentUser();
        logger.info("getting package price...");
        double iatPrice =gatIatPrice(buyIatDto.getIatPackage());
        logger.info("getting user wallet...");
        Wallet wallet =walletService.getByContact(user.getContact());
        if(wallet.getBalance()>=iatPrice){
            logger.info("tax account gen...");
            Date expireD= getExpireAt(buyIatDto.getIatPackage());
            IatAccount iatAccount =new IatAccount();
            iatAccount.setExpireAt(expireD);
            iatAccount.setLastTransaction(1);
            iatAccount.setContact(buyIatDto.getContact());
            logger.info("transacting...");
            TransactionDto transactionDto =generateTrans(buyIatDto.getIsp(), iatPrice,buyIatDto.getContact(), user.getContact());
            logger.info("saving transaction...");
            Transaction transaction=transactionService.addNew(transactionDto);
            Payment payment =generatePayment(buyIatDto.getContact(), wallet.getId(), transaction.getId(), iatPrice);
            logger.info("updating iat...");
            iatService.updateIat(new Iat(1,buyIatDto.getIsp(),iatPrice));
            logger.info("updating tax account...");
            iatAccountService.updateAccount(iatAccount, buyIatDto.getIatPackage());
            logger.info("saving payment...");
            logger.info("updating wallet...");
            walletService.updateWallet(paymentDao.save(payment));
            return payment;
        }

        throw new InvalidValuesException("insufficient balance");
    }

    private Payment generatePayment(String contact, int wId, int tId, double amount) {
        Payment payment= new Payment();
        payment.setWalletId(wId);
        payment.setExternalId(""+tId);
        payment.setRef(contact);
        payment.setPaymentType("wallet");
        payment.setMessage("paid iat: "+contact);
        payment.setStatus("success");
        payment.setPaymentDate(new Date());
        payment.setCreationDateTime(new Date());
        payment.setAmount(amount*-1);
        payment.setPaymentMethod(PaymentMethod.WALLET);
        return  payment;
    }

    private TransactionDto generateTrans(int isp, double amount, String fr, String by) {
        TransactionDto transaction = new TransactionDto();
        transaction.setTransactionDate(new Date());
        transaction.setCreationDateTime(new Date());
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setIsp(isp);
        transaction.setUserId(1);
        transaction.setPaidFor(fr);
        transaction.setPaidBy(by);
        transaction.setAmount(amount);
        logger.info("contacting isp");
        boolean bool=sendToIsp(isp, amount, fr);
        if(bool){
            transaction.setStatus(TransactionStatus.SUCCESS);
        }
        return transaction;
    }

    private boolean sendToIsp(int isp, double amount, String contact) {
        return  true;
    }

    private Date getExpireAt(IatPackage iatPackage){
        if(iatPackage.equals(IatPackage.DAILY)){
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH,1);
            return cal.getTime();

        }else if(iatPackage.equals(IatPackage.WEEKLY)){
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.WEEK_OF_MONTH,1);
            return cal.getTime();
        }else if(iatPackage.equals(IatPackage.MONTHLY)){
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.MONTH,1);
            return cal.getTime();
        }else if(iatPackage.equals(IatPackage.YEARLY)){
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.YEAR, 1);
            return cal.getTime();
        }
        throw new InvalidValuesException("invalid option");
    }

    private double gatIatPrice(IatPackage iatPackage) {
        if(iatPackage.equals(IatPackage.DAILY)){
            return 20;
        }else if(iatPackage.equals(IatPackage.WEEKLY)){
            return 140;
        }else if(iatPackage.equals(IatPackage.MONTHLY)){
            return 600;
        } else if(iatPackage.equals(IatPackage.YEARLY)){
            return 7200;
        }
        throw new InvalidValuesException("invalid option...");
    }

    @Override
    public List<Payment> myLastFive() {
        logger.info("getting current user...");
        User user=myUserDetailsService.currentUser();
        logger.info("getting  user wallet...");
        Wallet wallet =walletService.getByUser(user.getId());
        logger.info("getting payments...");
        return paymentDao.findFirst5ByWalletIdAndStatusOrderByIdDesc(wallet.getId(), "success");
    }

    @Override
    public List<Payment> myPayments() {
        logger.info("getting current user...");
        User user=myUserDetailsService.currentUser();
        logger.info("getting  user wallet...");
        Wallet wallet =walletService.getByUser(user.getId());
        return paymentDao.findByWalletIdAndStatusOrderByIdDesc(wallet.getId(), "success");
    }

    @Override
    public List<Payment> lastFifty(int walletId) {
        return paymentDao.findFirst50ByWalletIdAndStatusOrderByIdDesc(walletId, "success");
    }

    @Override
    public Payment transfer(double amount, String contact) {
        logger.info("getting current user...");
        User user=myUserDetailsService.currentUser();
        logger.info("getting  user wallet...");
        Wallet wallet =walletService.getByUser(user.getId());
        if(wallet.getBalance()>amount) {
            logger.info("getting  receiver wallet...");
            Wallet rWallet = walletService.getByContact(contact);
            logger.info("transacting...");
            TransactionDto transactionDto = generateTrans(10, amount, contact, user.getContact());
            transactionDto.setStatus(TransactionStatus.SUCCESS);
            logger.info("saving transaction...");
            Transaction transaction = transactionService.addNew(transactionDto);
            logger.info("payment gen...");
            Payment payment = generatePayment(contact, wallet.getId(), transaction.getId(), amount);
            payment.setMessage("transferred to " + contact);
            logger.info("updating sender wallet...");
            walletService.updateWallet(paymentDao.save(payment));
            logger.info("updating receiver wallet...");
            Payment payment1 = generatePayment(user.getContact(), rWallet.getId(), transaction.getId(), amount * -1);
            payment1.setMessage("received from wallet " + user.getContact());
            walletService.updateWallet(paymentDao.save(payment1));
            return payment;
        }
        throw new InvalidValuesException("Insufficient balance");

    }


}

