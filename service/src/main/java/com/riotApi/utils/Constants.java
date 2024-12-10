package com.riotApi.utils;

public class Constants {

    public static final String BASE_URL= "/api";

  public static final String RIOT_CACHE= "RiotCache";
  public static final String RIOT_CACHE_LONG = "apiResponses";
  public static final String ACCOUNT_CACHE=  "'account-' + #summonerName";
  public static final String MATCHES_CACHE= "'matches-' + #puuid + '-' + #start + '-' +#count";
  public static final String MATCH_CACHE= "'matches-' + #matchId";
  public static final String MATCH_HISTORY_CACHE= "'matchHistory-' + #summonerName + '-' + #start";


  public static final String RESPONSE_SUCCESS = "Success";
    public static final String ACCOUNT_NAME= "{summonersName}";
    public static final String ACCOUNT_TAG= "{tag}";
    public static final String PUUID_TAG= "{puuid}";
    public static final String START= "{start}";
    public static final String COUNT= "{count}";
    public static final String MATCH_ID= "{matchId}";



    public static final String ACCOUNT_BY_RIOT_ID = "riot/account/v1/accounts/by-riot-id/" + ACCOUNT_NAME + "/" + ACCOUNT_TAG;
    public static final String MATCH_BY_PUUID = "lol/match/v5/matches/by-puuid/" + PUUID_TAG + "/ids?start=" + START + "&count=" + COUNT;
    public static final String MATCH_BY_MATCH_ID= "lol/match/v5/matches/" + MATCH_ID;




}
