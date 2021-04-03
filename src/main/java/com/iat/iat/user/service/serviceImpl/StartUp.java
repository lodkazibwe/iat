package com.iat.iat.user.service.serviceImpl;

import com.iat.iat.account.model.Deposit;
import com.iat.iat.account.service.DepositService;
import com.iat.iat.payment.model.PaymentMethod;
import com.iat.iat.user.dto.AdminUserDto;
import com.iat.iat.user.model.AdminUser;
import com.iat.iat.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartUp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired DepositService depositService;
    @Autowired AdminService adminService;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        boolean bool =depositService.existByMethod(PaymentMethod.FLUTTER_WAVE);
        if(bool){
            adminService.addRole("0000", "ADMIN");

        }else{
            Deposit deposit= new Deposit();
            deposit.setAmount(0);
            deposit.setPaymentMethod(PaymentMethod.FLUTTER_WAVE);
            depositService.addDeposit(deposit);
            adminService.addSuperUser();

            //userService.addRole(contact,"ADMIN");

        }





    }
}
