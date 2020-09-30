insert into oauth_client_details (client_id, client_secret,
    resource_ids, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information,
    autoapprove, logout_uri, base_uri)
  values ('taeu_client', 'taeu_secret',
    null, 'read', 'authorization_code',
    'http://localhost:8080/oauthCallback', 'ROLE_YOUR_CLIENT', 36000,
    2592000, null,
    'true', 'http://localhost:8080/logout', 'http://localhost:8080/me');