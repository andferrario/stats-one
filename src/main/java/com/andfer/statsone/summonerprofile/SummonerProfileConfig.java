package com.andfer.statsone.summonerprofile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SummonerProfileConfig {

    @Bean
    RetrieveSummonerProfileUseCase retrieveSummonerProfileUseCase() {
        return new RetrieveSummonerProfileUseCase();
    }
}
