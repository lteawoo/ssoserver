package kr.taeu.ssoserver.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final CorsConfigurationSource corsConfigurationSource;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    /**
     * 토큰 엔드포인트에 대한 보안 제약을 정의
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
        authorizationServerSecurityConfigurer.addTokenEndpointAuthenticationFilter(corsFilter);
        authorizationServerSecurityConfigurer.checkTokenAccess("isAuthenticated()");
    }

    /**
     * 인가와 토큰 엔드포인트, 토큰 서비스를 정의
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(jdbcAuthorizationCodeServices())
                .approvalStore(jdbcApprovalStore())
                .userDetailsService(userDetailsService)
                .setClientDetailsService(jdbcClientDetailsService());
    }

    /**
     * JWT Converter 등록
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // JWT 사용 설정
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("test");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * Auth Code를 DB로 관리
     */
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 인가 정보를 DB로 관리
     */
    @Bean
    public ApprovalStore jdbcApprovalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * Client 정보를 DB로 관리
     */
    @Bean
    @Primary
    public ClientDetailsService jdbcClientDetailsService() throws Exception {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsServiceBuilder()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                .build();
        return clientDetailsService;
    }
}
