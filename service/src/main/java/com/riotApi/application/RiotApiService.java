package com.riotApi.application;

import com.riotApi.domain.input.RiotApiInputPort;
import com.riotApi.domain.output.RiotApiOutputPort;
import com.riotApi.dto.Account.AccountDto;
import com.riotApi.dto.Match.MatchDto;
import com.riotApi.utils.Constants;
import com.riotApi.utils.CustomObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.util.Map;


@Service
public class RiotApiService implements RiotApiInputPort {

  @Value("${riot-api.baseUrl}")
  private String riotApiBaseUrl;

  @Value("${apikey}")
  private String apiKey;

  private final CustomObjectMapper mapper;


  private final RiotApiOutputPort riotApiOutputPort;

  public RiotApiService(CustomObjectMapper mapper, RiotApiOutputPort riotApiOutputPort) {
    this.mapper = mapper;
    this.riotApiOutputPort = riotApiOutputPort;
  }


  @Cacheable(value =Constants.RIOT_CACHE_LONG , key = Constants.ACCOUNT_CACHE)
  @Override
  public AccountDto getAccount(String summonerName) {
    String[] accountName = summonerName.split("-");

    if(accountName.length !=2){
      return null;
    }

    Map<String, String> replacements = new HashMap<>();
    replacements.put(Constants.ACCOUNT_NAME, accountName[0]);
    replacements.put(Constants.ACCOUNT_TAG, accountName[1]);

    return riotApiOutputPort.doCallGetAccount(
        mapper.mapUrl(
            replacements,
              riotApiBaseUrl.concat(Constants.ACCOUNT_BY_RIOT_ID)
        ),
        apiKey
    );
  }

  @Cacheable(value = Constants.RIOT_CACHE, key =Constants.MATCHES_CACHE)
  @Override
  public List<String> getMatches(String puuid, Integer start, Integer count) {

    if(start==null) start = 0;
    if(count==null) count = 100;

    Map<String, String> replacements = new HashMap<>();
    replacements.put(Constants.PUUID_TAG, puuid);
    replacements.put(Constants.START, String.valueOf(start));
    replacements.put(Constants.COUNT, String.valueOf(count));


    return riotApiOutputPort.doCallGetMatchesIds(
        mapper.mapUrl(
            replacements,
            riotApiBaseUrl.concat(Constants.MATCH_BY_PUUID)
        ),
        apiKey
    );
  }


  @Cacheable(value = Constants.RIOT_CACHE_LONG, key = Constants.MATCH_CACHE )
  @Override
  public MatchDto getMatchById(String matchId) {

    Map<String, String> replacements = new HashMap<>();
    replacements.put(Constants.MATCH_ID, matchId);


    return riotApiOutputPort.doCallGetMatch(
        mapper.mapUrl(
            replacements,
            riotApiBaseUrl.concat(Constants.MATCH_BY_MATCH_ID)
        ),
        apiKey
    );
  }

  @Cacheable(value = Constants.RIOT_CACHE, key = Constants.MATCH_HISTORY_CACHE)
  @Override
  public List<MatchDto> getMatchesBySummonerName(final String summonerName, int start) {
    AccountDto cuenta = getAccount(summonerName);

    List<String> partidasString = getMatches(cuenta.getPuuid(), start, 25);

    List<MatchDto> partidas = new ArrayList<MatchDto>();


    for(String partida: partidasString){
      partidas.add(getMatchById(partida));
    }

    return partidas;

  }

}
