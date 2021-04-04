package com.iat.iat.user.service.serviceImpl;

import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.service.DepositService;
import com.iat.iat.isp.dto.ISPDto;
import com.iat.iat.isp.service.ISPService;
import com.iat.iat.payment.model.PaymentMethod;
import com.iat.iat.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartUp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired DepositService depositService;
    @Autowired AdminService adminService;
    @Autowired ISPService ispService;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        boolean bool =depositService.existByMethod(PaymentMethod.FLUTTER_WAVE);
        if(bool){
            ispService.addIsp(new ISPDto(1,"MTN", "example.com"));

        }else{
            Deposit deposit= new Deposit();
            deposit.setAmount(0);
            deposit.setPaymentMethod(PaymentMethod.FLUTTER_WAVE);
            depositService.addDeposit(deposit);
            adminService.addSuperUser();
            adminService.addRole("Admin", "ADMIN");


        }


    }
}
