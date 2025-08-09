package com.wallet_service.controller;

import com.wallet_service.dto.WalletDto;
import com.wallet_service.model.Wallet;
import com.wallet_service.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WalletControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WalletService walletService;

    private WalletController walletController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        walletController = new WalletController(walletService);
        mockMvc = MockMvcBuilders.standaloneSetup(walletController).build();
    }

    @Test
    public void testGetAllWallets() throws Exception {
        WalletDto wallet = new WalletDto();
        wallet.setId(1L);
        wallet.setWalletName("Test Wallet");
        wallet.setCurrency(Wallet.Currency.USD);
        wallet.setActiveForShopping(true);
        wallet.setActiveForWithdraw(false);
        wallet.setBalance(BigDecimal.valueOf(0));
        wallet.setUsableBalance(BigDecimal.valueOf(0));

        Mockito.when(walletService.getAllWallets()).thenReturn(Collections.singletonList(wallet));

        mockMvc.perform(get("/api/wallets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].walletName", is("Test Wallet")));
    }

    @Test
    public void testCreateWallet() throws Exception {
        WalletDto dto = new WalletDto();
        dto.setId(1L);
        dto.setWalletName("Test Wallet");
        dto.setCurrency(Wallet.Currency.USD);
        dto.setActiveForShopping(true);
        dto.setActiveForWithdraw(false);
        dto.setBalance(BigDecimal.valueOf(0));
        dto.setUsableBalance(BigDecimal.valueOf(0));

        Mockito.when(walletService.createWallet(Mockito.any(WalletDto.class))).thenReturn(dto);

        String json = """
                {
                    "walletName": "Test Wallet",
                    "currency": "USD",
                    "activeForShopping": true,
                    "activeForWithdraw": false
                }
                """;

        mockMvc.perform(post("/api/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.walletName", is("Test Wallet")))
                .andExpect(jsonPath("$.currency", is("USD")));
    }
}