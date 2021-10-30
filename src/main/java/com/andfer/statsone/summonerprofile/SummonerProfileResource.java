package com.andfer.statsone.summonerprofile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummonerProfileResource {

    private final RetrieveSummonerProfileUseCase retrieveSummonerProfileUseCase;

    public SummonerProfileResource(RetrieveSummonerProfileUseCase retrieveSummonerProfileUseCase) {
        this.retrieveSummonerProfileUseCase = retrieveSummonerProfileUseCase;
    }

    @GetMapping("/summoner/profile/by-name/{summonerName}")
    public SummonerProfile getSummonerProfileByName(@PathVariable String summonerName) {
        return retrieveSummonerProfileUseCase.execute(summonerName);
    }
}
