INSERT INTO OAUTH_CLIENT_DETAILS (CLIENT_ID, CLIENT_SECRET,
    RESOURCE_IDS, SCOPE, AUTHORIZED_GRANT_TYPES,
    WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY,
    REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION,
    AUTOAPPROVE, LOGOUT_URI, BASE_URI)
  VALUES ('taeu_client', 'taeu_secret',
    NULL, 'read', 'authorization_code,refresh_code',
    'http://localhost:8091/oauth/callback', 'MY_CLIENT', 36000,
    2592000, NULL,
    'true', 'http://localhost:8080/logout', 'http://localhost:8080/me');