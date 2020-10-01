package kr.taeu.ssoserver.oauth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OAUTH_ACCESS_TOKEN")
public class AccessToken {
    @Id
    @Column(name="TOKEN_ID")
    private String tokenId;

    @Column(name="TOKEN")
    private String token;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="AUTHENTICATION_ID")
    private String authenticationId;

    @Column(name="CLIENT_ID")
    private String clientId;

    @Column(name="AUTHENTICATION")
    private String authentication;
}
