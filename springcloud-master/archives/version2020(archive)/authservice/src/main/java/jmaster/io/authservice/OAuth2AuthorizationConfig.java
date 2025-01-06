package jmaster.io.authservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	clients.inMemory().withClient("jmaster").secret(passwordEncoder.encode("123"))
		.authorizedGrantTypes("password", "refresh_token").scopes("read", "write")
		.accessTokenValiditySeconds(3600) // 1 hour
		.refreshTokenValiditySeconds(2592000); // 30 days;
    }

    @Bean
    public TokenStore tokenStore() {
	return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
		//.accessTokenConverter(accessTokenConverter())
		.userDetailsService(userDetailsService);//check user when refresh
    }

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//	oauthServer.tokenKeyAccess("permitAll()") // path: /oauth/token
//		.checkTokenAccess("isAuthenticated()") // path: /oauth/check-token
//		.passwordEncoder(passwordEncoder);
//    }

////	JWT
//    @Bean
//    public TokenStore tokenStore() {
//	return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//	converter.setSigningKey("123");// symmetric key - ko bao mat
//
////		KeyStoreKeyFactory keyStoreKeyFactory = 
////			      new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
////			    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
//
//	return converter;
//    }
//
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//	final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//	defaultTokenServices.setTokenStore(tokenStore());
//	defaultTokenServices.setSupportRefreshToken(true);
//	return defaultTokenServices;
//    }

}
