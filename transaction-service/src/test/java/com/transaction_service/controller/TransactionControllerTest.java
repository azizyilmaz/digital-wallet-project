package com.transaction_service.controller;

import com.transaction_service.dto.TransactionDto;
import com.transaction_service.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    private TransactionController transactionController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        transactionController = new TransactionController(transactionService);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

//    @Test
//    public void testDepositTransaction() throws Exception {
//        TransactionDto dto = new TransactionDto();
//        dto.setId(1L);
//        dto.setAmount(BigDecimal.valueOf(1500));
//        dto.setWalletId(101L);
//        dto.setDescription("Test datası");
//        dto.setIban("TR123");
//
//        Mockito.when(transactionService.deposit(Mockito.any(TransactionDto.class))).thenReturn(dto);
//
//        String json = """
//                {
//                  "id": 1,
//                  "amount": 1500,
//                  "walletId": 101,
//                  "description": "Test datası",
//                  iban: "TR123"
//                }
//                """;
//
//        mockMvc.perform(post("/api/transactions/deposit").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(1L))).andExpect(jsonPath("$.amount", is(1500)));
//    }
}