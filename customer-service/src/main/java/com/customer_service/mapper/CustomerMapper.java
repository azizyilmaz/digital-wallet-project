package com.customer_service.mapper;

import com.customer_service.dto.CustomerDto;
import com.customer_service.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    // Entity -> DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "tckn", target = "tckn")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    CustomerDto toDto(Customer entity);

    // DTO -> Entity
    @Mapping(source = "id", target = "id")
    @Mapping(source = "tckn", target = "tckn")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    Customer toEntity(CustomerDto dto);
}
