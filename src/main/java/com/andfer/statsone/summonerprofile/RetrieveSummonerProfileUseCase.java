package com.andfer.statsone.summonerprofile;

import java.util.Optional;

public class RetrieveSummonerProfileUseCase {

    public RetrieveSummonerProfileUseCase() {
    }

    public Optional<SummonerProfile> execute(String summonerName) {
        return Optional.of(new SummonerProfile(summonerName, 145, 28));
    }
}