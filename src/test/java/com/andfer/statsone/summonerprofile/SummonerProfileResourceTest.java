package com.andfer.statsone.summonerprofile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class SummonerProfileResourceTest {
    public static final String A_SUMMONER_NAME = "aSummonerName";
    public static final String A_NOT_EXISTING_SUMMONER_NAME = "ANotExistingSummonerName";
    public static final long A_LEVEL = 30L;
    public static final int AN_ICON_ID = 28;
    public static final SummonerProfile A_SUMMONER_PROFILE = new SummonerProfile(A_SUMMONER_NAME, A_LEVEL, AN_ICON_ID);

    @Mock
    private RetrieveSummonerProfileUseCase retrieveSummonerProfileUseCase;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(new SummonerProfileResource(retrieveSummonerProfileUseCase)).build();
    }

    @Test
    void returnAValidSummonerProfile() throws Exception {
        when(retrieveSummonerProfileUseCase.execute(A_SUMMONER_NAME))
                .thenReturn(Optional.of(A_SUMMONER_PROFILE));

        mockMvc.perform(get("/summoner/profile/by-name/%s".formatted(A_SUMMONER_NAME)))
                .andExpect(status().is2xxSuccessful());

        verify(retrieveSummonerProfileUseCase, times(1))
                .execute(A_SUMMONER_NAME);
    }

    @Test
    void whenNoSummonerIsFoundReturns4xx() throws Exception {
        when(retrieveSummonerProfileUseCase.execute(A_NOT_EXISTING_SUMMONER_NAME))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/summoner/profile/by-name/%s".formatted(A_NOT_EXISTING_SUMMONER_NAME)))
                .andExpect(status().is4xxClientError());

        verify(retrieveSummonerProfileUseCase, times(1))
                .execute(A_NOT_EXISTING_SUMMONER_NAME);
    }
}