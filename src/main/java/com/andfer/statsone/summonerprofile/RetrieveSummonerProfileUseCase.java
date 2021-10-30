package com.andfer.statsone.summonerprofile;

public class RetrieveSummonerProfileUseCase {

    public RetrieveSummonerProfileUseCase() {}

    public SummonerProfile execute(String summonerName) {
        return new SummonerProfile(summonerName, 145, 28);
    }
}