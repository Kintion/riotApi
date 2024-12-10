package com.riotApi.domain.output;

import com.riotApi.dto.Account.AccountDto;
import com.riotApi.dto.Match.MatchDto;
import java.util.List;

public interface RiotApiOutputPort {

  AccountDto doCallGetAccount(final String path, final String apiKey);
  List<String> doCallGetMatchesIds(final String path, final String apiKey);
  MatchDto doCallGetMatch(final String path, final String apiKey);


}
