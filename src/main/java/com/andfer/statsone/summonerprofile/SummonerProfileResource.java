package com.andfer.statsone.summonerprofile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class SummonerProfileResource {

    private final RetrieveSummonerProfileUseCase retrieveSummonerProfileUseCase;

    public SummonerProfileResource(RetrieveSummonerProfileUseCase retrieveSummonerProfileUseCase) {
        this.retrieveSummonerProfileUseCase = retrieveSummonerProfileUseCase;
    }

    @GetMapping("/summoner/profile/by-name/{summonerName}")
    public ResponseEntity<SummonerProfile> getSummonerProfileByName(@PathVariable String summonerName) {
        var result = retrieveSummonerProfileUseCase.execute(summonerName);

        return result
                .map(summonerProfile -> new ResponseEntity<>(summonerProfile, OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
