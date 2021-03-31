package com.iat.iat.wallet.converter;

import com.iat.iat.wallet.dto.WalletDto;
import com.iat.iat.wallet.model.Wallet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletConverter {
    public WalletDto entityToDto(Wallet wallet){
        WalletDto walletDto =new WalletDto();
        walletDto.setBalance(wallet.getBalance());
        walletDto.setId(wallet.getId());
        walletDto.setUid(wallet.getUid());
        walletDto.setLastPayment(wallet.getLastPayment());
        return walletDto;
    }

    public Wallet dtoToEntity(WalletDto walletDto){
        Wallet wallet =new Wallet();
        wallet.setBalance(walletDto.getBalance());
        wallet.setUid(walletDto.getUid());
        return wallet;
    }

    public List<WalletDto> entityToDto(List<Wallet> wallets){
        return wallets.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Wallet> dtoToEntity(List<WalletDto> walletDtos){
        return walletDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
