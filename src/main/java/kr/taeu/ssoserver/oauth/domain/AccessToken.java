package kr.taeu.ssoserver.oauth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oauth_access_token")
public class AccessToken {
    @Id
    @Column(name="tokenId")
    private String tokenId;

    @Column(name="token")
    private String token;

    @Column(name="user_name")
    private String userName;

    @Column(name="authentication_id")
    private String authenticationId;

    @Column(name="client_id")
    private String clientId;

    @Column(name="authentication")
    private String authentication;
}
