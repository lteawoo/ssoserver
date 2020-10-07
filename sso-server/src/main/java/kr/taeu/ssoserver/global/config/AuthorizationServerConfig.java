package kr.taeu.ssoserver.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter())
                .authorizationCodeServices(jdbcAuthorizationCodeServices())
                .approvalStore(jdbcApprovalStore())
                .setClientDetailsService(jdbcClientDetailsService());
    }

    /**
     * JWT Converter 등록
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // JWT 사용 설정
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("test");
        return jwtAccessTokenConverter;
    }

    /**
     * Auth Code를 DB로 관리
     * @return AuthorizationCodeServices
     */
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 인가 정보를 DB로 관리
     * @return ApprovalStore
     */
    @Bean
    public ApprovalStore jdbcApprovalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * Client 정보를 DB로 관리
     * @return ClientDetailsService
     * @throws Exception
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
