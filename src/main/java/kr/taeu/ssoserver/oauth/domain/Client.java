package kr.taeu.ssoserver.oauth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OAUTH_CLIENT_DETAILS")
public class Client {
    @Id
    @Column(name="CLIENT_ID")
    private String clientId;

    @Column(name="WEB_SERVER_REDIRECT_URI")
    private String redirectUri;

    @Column(name="LOGOUT_URI")
    private String logoutUri;

    @Column(name="BASE_URI")
    private String baseUri;
}
