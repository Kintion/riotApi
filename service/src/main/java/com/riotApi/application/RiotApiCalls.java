package com.riotApi.application;
import com.riotApi.abstracts.AbstractEndpoint;
import com.riotApi.domain.output.RiotApiOutputPort;
import com.riotApi.dto.Account.AccountDto;
import com.riotApi.dto.Match.MatchDto;
import com.riotApi.utils.CustomObjectMapper;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RiotApiCalls extends AbstractEndpoint implements RiotApiOutputPort {

    public RiotApiCalls(final RestTemplate restTemplate, final CustomObjectMapper customObjectMapper){
      super(restTemplate, customObjectMapper);
    }

    @Override
    public AccountDto doCallGetAccount(final String path, final String apiKey) {
      return doCall(path, HttpMethod.GET, addDefaultHeaders(apiKey), null, AccountDto.class);
    }

    @Override
    public List<String> doCallGetMatchesIds(final String path, final String apiKey){
      return doCall(path, HttpMethod.GET, addDefaultHeaders(apiKey), null, List.class);
    }

    @Override
    public MatchDto doCallGetMatch(final String path, final String apiKey){
      return doCall(path, HttpMethod.GET, addDefaultHeaders(apiKey), null, MatchDto.class);
    }


}
