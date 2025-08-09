package com.wallet_service.mapper;

import com.wallet_service.dto.WalletDto;
import com.wallet_service.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    // Entity -> DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "walletName", target = "walletName")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "activeForShopping", target = "activeForShopping")
    @Mapping(source = "activeForWithdraw", target = "activeForWithdraw")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "usableBalance", target = "usableBalance")
    @Mapping(source = "customerId", target = "customerId")
    WalletDto toDto(Wallet wallet);

    // DTO -> Entity
    @Mapping(source = "id", target = "id")
    @Mapping(source = "walletName", target = "walletName")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "activeForShopping", target = "activeForShopping")
    @Mapping(source = "activeForWithdraw", target = "activeForWithdraw")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "usableBalance", target = "usableBalance")
    @Mapping(source = "customerId", target = "customerId")
    Wallet toEntity(WalletDto dto);
}
