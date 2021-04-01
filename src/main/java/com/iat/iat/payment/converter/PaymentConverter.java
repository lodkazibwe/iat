package com.iat.iat.payment.converter;

import com.iat.iat.payment.dto.PaymentDto;
import com.iat.iat.payment.model.Payment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentConverter {

    public PaymentDto entityToDto(Payment payment){
        PaymentDto paymentDto =new PaymentDto();
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setId(payment.getId());
        paymentDto.setCreationDateTime(payment.getCreationDateTime());
        paymentDto.setPaymentDate(payment.getPaymentDate());
        paymentDto.setWalletId(payment.getWalletId());
        paymentDto.setPaymentMethod(payment.getPaymentMethod());
        paymentDto.setMessage(payment.getMessage());
        paymentDto.setStatus(payment.getStatus());
        paymentDto.setExternalId(payment.getExternalId());
        paymentDto.setPaymentType(payment.getPaymentType());
        paymentDto.setRef(payment.getRef());
        return paymentDto;
    }

    public Payment dtoToEntity(PaymentDto paymentDto){
        Payment payment =new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setId(paymentDto.getId());
        payment.setWalletId(paymentDto.getWalletId());
        payment.setPaymentDate(new Date());
        payment.setCreationDateTime(new Date());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        return payment;
    }

    public List<PaymentDto> entityToDto(List<Payment> payments){
        return  payments.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Payment> dtoToEntity(List<PaymentDto> paymentDtos){
        return  paymentDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
