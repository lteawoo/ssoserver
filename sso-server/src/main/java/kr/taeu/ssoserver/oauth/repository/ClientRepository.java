package kr.taeu.ssoserver.oauth.repository;

import kr.taeu.ssoserver.oauth.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
