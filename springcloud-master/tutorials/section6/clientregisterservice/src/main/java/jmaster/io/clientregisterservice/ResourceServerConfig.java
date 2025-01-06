package jmaster.io.clientregisterservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@SuppressWarnings("deprecation")
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().antMatchers("/actuator/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll();
    }

    /// JWT
    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
	config.tokenStore(tokenStore());
    }

    @Bean
    public TokenStore tokenStore() {
	return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	// converter.setSigningKey("123");// symmetric key

	// asymmetric key is more secure
	Resource resource = new ClassPathResource("public.txt");
	String publicKey = null;
	try {
	    publicKey = new BufferedReader(new InputStreamReader(resource.getInputStream())).lines()
		    .collect(Collectors.joining("\n"));
	} catch (final IOException e) {
	    throw new RuntimeException(e);
	}
	converter.setVerifierKey(publicKey);

	return converter;
    }

}