package accountservice.accountservice.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import accountservice.accountservice.model.StatisticDTO;
import feign.RequestInterceptor;

@SuppressWarnings("deprecation")
@FeignClient(name = "statistic-service", fallback = StatisticServiceImpl.class, configuration = StatisticFeignClientConfiguration.class)
public interface StatisticService {

    @PostMapping("/statistic")
    void add(@RequestBody StatisticDTO statisticDTO);

}

@Component
class StatisticServiceImpl implements StatisticService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void add(StatisticDTO statisticDTO) {
	// fallback
	logger.error("Statistic service is slow");
    }
}

@SuppressWarnings("deprecation")
class StatisticFeignClientConfiguration {

    @Value("${server.oauth2.token.url}")
    private String tokenURL;

    @Value("${client.statistic.client.id}")
    private String clientId;

    @Value("${client.statistic.client.secret}")
    private String clientSecret;

    @Value("${client.statistic.client.scopes}")
    private List<String> scopes;

    @Bean
    public RequestInterceptor requestInterceptor() {
	return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
    }

    private OAuth2ProtectedResourceDetails resource() {
	final ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
	details.setAccessTokenUri(tokenURL);
	details.setClientId(clientId);
	details.setClientSecret(clientSecret);
	details.setScope(scopes);
	return details;
    }
}
