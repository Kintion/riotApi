package com.riotApi.controller;
import com.riotApi.domain.input.RiotApiInputPort;
import com.riotApi.dto.Account.AccountDto;
import com.riotApi.dto.Match.MatchDto;
import com.riotApi.requestResponse.RequestResponseModel;
import com.riotApi.utils.Constants;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.BASE_URL)
public class RiotApiController {


  private final RiotApiInputPort riotApiInputPort;


  @GetMapping(path = "account/{summonersName}", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<RequestResponseModel<AccountDto>> getAccount(@PathVariable final String summonersName) {
    return ResponseEntity.ok(new RequestResponseModel<>(riotApiInputPort.getAccount(summonersName), Constants.RESPONSE_SUCCESS));
  }

  @GetMapping(path = "account/matchList/{puuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<RequestResponseModel<List<String>>> getMatchesId(@PathVariable final String puuid) {
    return ResponseEntity.ok(new RequestResponseModel<>(riotApiInputPort.getMatches(puuid, null ,null), Constants.RESPONSE_SUCCESS));
  }

  @GetMapping(path = "account/match/{matchId}", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<RequestResponseModel<MatchDto>> getMatch(@PathVariable final String matchId) {
    return ResponseEntity.ok(new RequestResponseModel<>(riotApiInputPort.getMatchById(matchId), Constants.RESPONSE_SUCCESS));
  }



  @GetMapping(path = "account/matches/{summonersName}", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<RequestResponseModel<List<MatchDto>>> getMatchBySummonerName(@PathVariable final String summonersName, @RequestParam(name = "start", defaultValue = "0") int start) {
    return ResponseEntity.ok(new RequestResponseModel<>(riotApiInputPort.getMatchesBySummonerName(summonersName, start), Constants.RESPONSE_SUCCESS));
  }


}
