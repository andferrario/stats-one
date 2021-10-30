package com.andfer.statsone.summonerprofile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class SummonerProfileResourceTest {
    public static final String A_SUMMONER_NAME = "aSummonerName";
    public static final long A_LEVEL = 30L;
    public static final int AN_ICON_ID = 28;

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
                .thenReturn(new SummonerProfile(A_SUMMONER_NAME, A_LEVEL, AN_ICON_ID));

        mockMvc.perform(get("/summoner/profile/by-name/aSummonerName"))
                .andExpect(status().is2xxSuccessful());

        verify(retrieveSummonerProfileUseCase, times(1)).execute(A_SUMMONER_NAME);
    }
}