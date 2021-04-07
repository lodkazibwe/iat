package com.iat.iat.user.dto;

import com.iat.iat.wallet.dto.WalletDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private UserDto userDto;
    private WalletDto walletDto;
}
