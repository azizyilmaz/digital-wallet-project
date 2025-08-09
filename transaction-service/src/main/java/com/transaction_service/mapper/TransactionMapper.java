package com.transaction_service.mapper;

import com.transaction_service.dto.TransactionDto;
import com.transaction_service.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    // Entity -> DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "iban", target = "iban")
    @Mapping(source = "walletId", target = "walletId")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "description", target = "description")
    TransactionDto toDto(Transaction transaction);

    // DTO -> Entity
    @Mapping(source = "id", target = "id")
    @Mapping(source = "iban", target = "iban")
    @Mapping(source = "walletId", target = "walletId")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "description", target = "description")
    Transaction toEntity(TransactionDto dto);
}
