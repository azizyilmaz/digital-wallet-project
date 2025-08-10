package com.wallet_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long id;
    private String tckn;
    private String firstName;
    private String lastName;
}
