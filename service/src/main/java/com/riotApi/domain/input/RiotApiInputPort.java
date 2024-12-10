package com.riotApi.domain.input;

import com.riotApi.dto.Account.AccountDto;
import com.riotApi.dto.Match.MatchDto;
import java.util.List;

public interface RiotApiInputPort {


  AccountDto getAccount(final String summonerName);
  List<String> getMatches(final String puuid, Integer start, Integer count);
  MatchDto getMatchById(final String matchId);
  List<MatchDto> getMatchesBySummonerName(final String summonerName, int start);
}
